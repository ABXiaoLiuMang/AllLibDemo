package com.dale.utils;

import android.util.Log;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/09/21
 *     desc  : utils about log
 * </pre>
 */
public final class LogUtils {

    public static String customTagPrefix = "Dream";
    private static boolean isDebug = true;

    private LogUtils() {
    }

    public static void isDebug(boolean b) {
        isDebug = b;
    }

    private static String generateTag() {
        return generateTag(customTagPrefix);
    }

    private static String generateTag(String customTag) {
        StackTraceElement caller = new Throwable().getStackTrace()[2];
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = customTag + ":" + tag;
        return tag;
    }

    public static void d(String content) {
        if (!isDebug) return;
        String tag = generateTag(customTagPrefix);
        Log.d(tag, content);
    }

    public static void d(String customTag,String content) {
        if (!isDebug) return;
        String tag = generateTag(customTag);
        Log.d(tag, content);
    }

    public static void d(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();
        Log.d(tag, content, tr);
    }

    public static void e(String content) {
        if (!isDebug) return;
        String tag = generateTag();
        Log.e(tag, content);
    }

    public static void e(String customTag,String content) {
        if (!isDebug) return;
        String tag = generateTag(customTag);
        Log.e(tag, content);
    }

    public static void e(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();
        Log.e(tag, content, tr);
    }

    public static void i(String content) {
        if (!isDebug) return;
        String tag = generateTag();
        Log.i(tag, content);
    }

    public static void i(String customTag,String content) {
        if (!isDebug) return;
        String tag = generateTag(customTag);
        Log.i(tag, content);
    }

    public static void i(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();

        Log.i(tag, content, tr);
    }

    public static void v(String content) {
        if (!isDebug) return;
        String tag = generateTag();
        Log.v(tag, content);
    }

    public static void v(String customTag,String content) {
        if (!isDebug) return;
        String tag = generateTag(customTag);
        Log.v(tag, content);
    }

    public static void v(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();
        Log.v(tag, content, tr);
    }

    public static void w(String content) {
        if (!isDebug) return;
        String tag = generateTag();
        Log.w(tag, content);
    }

    public static void w(String customTag,String content) {
        if (!isDebug) return;
        String tag = generateTag(customTag);
        Log.w(tag, content);
    }

    public static void w(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();
        Log.w(tag, content, tr);
    }


}