package org.beryl.diagnostics;

import android.content.Intent;
import android.database.Cursor;

public class Logger {

	static final String NullString = "[NULL]";
	
	/** Creates a new default Logger instance. */
	public static final Log newInstance(String tag) {
		return new Log(new LogCatLogWriter(), tag);
	}
	
	private static final Log logDelegate = newInstance("Log");

	public static void d(String tag, Object obj) {
		logDelegate.d(tag, obj);
	}

	public static void e(String tag, Object obj) {
		logDelegate.e(tag, obj);
	}

	public static void i(String tag, Object obj) {
		logDelegate.i(tag, obj);
	}

	public static void w(String tag, Object obj) {
		logDelegate.w(tag, obj);
	}
	
	public static void d(String tag, String msg) {
		logDelegate.d(tag, msg);
	}

	public static void e(String tag, String msg) {
		logDelegate.e(tag, msg);
	}

	public static void i(String tag, String msg) {
		logDelegate.i(tag, msg);
	}

	public static void w(String tag, String msg) {
		logDelegate.w(tag, msg);
	}
	
	public static void d(String msg) {
		logDelegate.d(msg);
	}

	public static void e(Exception e) {
		logDelegate.e(e);
	}

	public static void e(String msg) {
		logDelegate.e(msg);
	}

	public static void i(String msg) {
		logDelegate.i(msg);
	}

	public static void w(String msg) {
		logDelegate.w(msg);
	}
	
	public static void d(Object obj) {
		logDelegate.d(obj);
	}

	public static void e(Object obj) {
		logDelegate.e(obj);
	}

	public static void i(Object obj) {
		logDelegate.i(obj);
	}

	public static void w(Object obj) {
		logDelegate.w(obj);
	}
	public static void e(String tag, Exception e) {
		logDelegate.e(tag, e);
	}

	public static void d(Intent intent) {
		logDelegate.d(intent);
	}

	public void inspectClass(Class<?> clazz) {
		logDelegate.inspectClass(clazz);
	}

	public void inspectQueryResult(final Cursor cur) {
		logDelegate.inspectQueryResult(cur);
	}

	public void inspectCursorData(final Cursor cur) {
		logDelegate.inspectCursorData(cur);
	}
}
