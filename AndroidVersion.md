## Introduction ##
Detecting which version of Android your app is running on is a bit of a pain because there's all this weird deprecated variable stuff. This class demystifies that and gives you the version in the form of the API level number. There's also convenience methods that take the guess work out of which API level you care about.

## Code ##
org.beryl.core.AndroidVersion is the class you care about for handling multiple version of Android.

The following example shows how you can load a class at runtime that supports multiple versions of Android without reflection. It uses AndroidVersion to determine which class to load into the interface.


```
import org.beryl.app.AndroidVersion;
public class StrictModeEnabler {

	/** Enables strict mode on the current thread. Note: Strict Mode is only supported in Gingerbread or higher. */
	public static void enableOnThread() {
		IStrictModeEnabler enabler = getStrictModeEnabler();
		enabler.startStrictMode();
	}
	
	private static IStrictModeEnabler getStrictModeEnabler() {
		if(AndroidVersion.isGingerbreadOrHigher()) {
			return new GingerbreadAndAboveStrictModeEnabler();
		} else {
			return new NoStrictModeEnabler();
		}
	}
}
```