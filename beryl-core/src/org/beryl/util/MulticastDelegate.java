package org.beryl.util;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/** Allows attachment of arbitrary and multiple Listener-style objects to be attached to a class.
<h2>Benefits</h2>
<ul>
	<li>No conditional null checking. Passes back a list of 0 elements if there's no listeners that fit.</li>
	<li>No explicit attaching of listeners. Listeners that fit are determined at runtime.</li>
	<li>Multiple objects can listen to the same event.</li>
	<li>Objects only need to be added once.</li>
	<li>Strongly typed.</li>
</ul>
<h2>Notes</h2>
<ul>
	<li>Order of which objects are returned is not guaranteed.</li>
	<li>Uses WeakReferences to hold objects internally. If an object is garbage collected it will no longer be available in the list.</li>
</ul>
<h2>Sample Code</h2>
<pre class="code"><code class="java">
interface OnPictureTakenListener {
	void onPictureTaken(Bitmap picture);
}

class PictureTaker {
	final MulticastDelegate listeners = new MulticastDelegate();
	
	public void addListener(Object obj) {
		listeners.add(obj);
	}
	
	// If you're calling a fire and forget delegate then use this. 
	public void takePictureForLazyDevelopers() {
		Bitmap picture = takePicture();
		listeners.invoke(OnPictureTakenListener.class, "onPictureTaken", picture);
	}
	// Use this if you need control over what and how delegates are called.
	public void takePicture() {
		Bitmap picture = takePicture();
		final List<OnPictureTakenListener> delegates = listeners.get(OnPictureTakenListener.class);
		for(OnPictureTakenListener delegate : delegates) {
			delegate.onPictureTaken(picture);
		}
	}
}
</code></pre>
 */
public class MulticastDelegate {

	final private ArrayList<WeakReference<Object>> delegates = new ArrayList<WeakReference<Object>>();
	
	/** Adds a delegate object. */
	public void add(final Object delegate) {
		if(!delegates.contains(delegate)) {
			delegates.add(new WeakReference<Object>(delegate));
		}
	}
	
	/** Gets a list of all delegate objects that implement the class/interface passed in. */
	@SuppressWarnings("unchecked")
	public <T> List<T> get(final Class<T> clazz) {
		final List<T> delegateList = new ArrayList<T>();
		
		ArrayList<WeakReference<Object>> deadObjects = null;
		
		for(WeakReference<Object> weakRef : delegates) {
			Object delegate = weakRef.get();
			if(delegate == null) {
				if(deadObjects == null) {
					deadObjects = new ArrayList<WeakReference<Object>>();
				}
				deadObjects.add(weakRef);
			} else {
				if(clazz.isAssignableFrom(delegate.getClass())) {
					delegateList.add((T)delegate);
				}
			}
		}
		
		compact(deadObjects);
		return delegateList;
	}
	
	/** Removes all delegates. */
	public void clear() {
		delegates.clear();
	}

	/** For the super lazy. Invoke the method without doing the manual leg work of iterating through all the objects.
	 * <i>This method can throw a runtime exceptions if the methodName cannot be resolved or an invoke of the method fails.</i> */
	public <T> void invoke(final Class<T> clazz, final String methodName, final Object... params) {
		List<T> delegateList = get(clazz);
		List<Class<?>> clazzParams = new ArrayList<Class<?>>();
		for(Object param : params) {
			clazzParams.add(param.getClass());
		}
		
		try {
			Method method = findMethod(clazz, methodName, clazzParams);
			for(T delegate : delegateList) {
				method.invoke(delegate, params);
			}
		} catch(RuntimeException e) {
			throw e;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private Method findMethod(final Class<?> clazz, final String methodName, final List<Class<?>> paramTypes) throws SecurityException, NoSuchMethodException {
		Method method = null;
		Method [] methods = clazz.getDeclaredMethods();
		for(Method candidate : methods) {
			if(candidate.getName().equals(methodName)) {
				
				Class<?>[] candidateParamTypes = candidate.getParameterTypes();
				if(candidateParamTypes.length == paramTypes.size()) {
					final int length = candidateParamTypes.length;
					boolean parametersMatch = true;
					for(int i = 0; i < length && parametersMatch; i++) {
						if(! candidateParamTypes[i].isAssignableFrom(paramTypes.get(i))) {
							parametersMatch = false;
						}
					}
					if(parametersMatch) {
						method = candidate;
						method.setAccessible(true);
						break;
					}
				}
			}
		}
		
		/* Try this to:
		method = clazz.getDeclaredMethod(methodName, (Class<?>[]) paramTypes.toArray());
		if(method != null) {
			method.setAccessible(true);
		}
		*/
		return method;
	}
	
	private void compact(final ArrayList<WeakReference<Object>> deadObjects) {
		if(deadObjects != null) {
			if(deadObjects != null) {
				for(WeakReference<Object> weakRef : deadObjects) {
					delegates.remove(weakRef);
				}
			}
		}
	}
}