package kr.blogspot.ovsoce.moviero.common;

public class Log {
	public final static boolean DEBUG = !false;
	public final static String TAG = "OJH";
	public static void d(String msg)
	{
		if(DEBUG && msg!=null) {
			String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
			String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
			String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
			int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

			android.util.Log.d(TAG, className + "." + methodName + "():" + lineNumber + " -> " + msg);
		}
	}
	public static void d(String tag, String msg) {
		d(msg);
	}
	public static void e(String msg)
	{
		if(DEBUG && msg!=null) {
			String fullClassName = Thread.currentThread().getStackTrace()[3].getClassName();
			String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
			String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
			int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

			android.util.Log.e(TAG, className + "." + methodName + "():" + lineNumber + " -> " + msg);
		}
	}
	public static void e(String tag, String msg) {
		e(msg);
	}
}
