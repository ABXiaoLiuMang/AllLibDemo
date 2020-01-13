package com.dale.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public final class SPUtils {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor shareEditor;
    private static SPUtils spUtils = null;

    private SPUtils(Context context, String path) {
        sharedPreferences = context.getSharedPreferences(path, Context.MODE_PRIVATE);
        shareEditor = sharedPreferences.edit();
    }

    public static SPUtils getInstance(Context context,String path) {
        if (spUtils == null) {
            synchronized (SPUtils.class) {
                if (spUtils == null) {
                    spUtils = new SPUtils(context, path);
                }
            }
        }

        return spUtils;
    }

    public static void put(String key, String value) {
        shareEditor.putString(key, value).apply();
    }

    public static String getString(String key) {
        return getString(key, "");
    }


    public static String getString(String key, final String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }


    public static void put(String key, int value) {
        shareEditor.putInt(key, value).apply();
    }

    public static int getInt(String key) {
        return getInt(key, -1);
    }

    public static int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static void put(String key, long value) {
        shareEditor.putLong(key, value).apply();
    }

    public static long getLong(String key) {
        return getLong(key, -1L);
    }

    public static long getLong(String key, final long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }


    public static void put(String key, final float value) {
        shareEditor.putFloat(key, value).apply();
    }


    public static float getFloat(String key) {
        return getFloat(key, -1f);
    }


    public static float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public static void put(String key, boolean value) {
        shareEditor.putBoolean(key, value).apply();
    }


    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }


    public static boolean getBoolean(String key, final boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }


    /**
     * 获取保存的所有键值对
     *
     * @return 所有保存数据
     */
    public static Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }

    public static boolean contains(String key) {
        return sharedPreferences.contains(key);
    }


    public static void remove(String key) {
        shareEditor.remove(key).apply();
    }

    /**
     * 清除所有数据
     */
    public static void clear() {
        shareEditor.clear().apply();
    }


}
