package com.dale.xweb.cache;

import android.text.TextUtils;

import java.util.HashSet;

public class CacheExtConfig {

    // 全局默认的
    private static HashSet STATIC = new HashSet() {
        {
//            add("html");
//            add("htm");
//            add("js");
//            add("ico");
//            add("css");
//            add("ttf");
//            add("woff");
//            add("woff2");
//            add("otf");
//            add("eot");
//            add("svg");
//            add("xml");
//            add("swf");
//            add("txt");
//            add("text");
//            add("conf");

            add("webp");
            add("png");
            add("jpg");
            add("jpeg");
            add("gif");
            add("bmp");
        }
    };

    // 不进行缓存的配置
    private static HashSet NO_CACH = new HashSet() {
        {
            add("mp4");
            add("mp3");
            add("ogg");
            add("avi");
            add("wmv");
            add("flv");
            add("rmvb");
            add("3gp");
        }
    };

    // 单独webview实例的
    private HashSet statics = new HashSet(STATIC);
    private HashSet noCache = new HashSet(NO_CACH);

    public static void addGlobalExt(String ext) {
        add(STATIC, ext);
    }

    public static void removeGlobalExt(String ext) {
        remove(STATIC, ext);
    }

    private static void add(HashSet set, String ext) {
        if (TextUtils.isEmpty(ext)) {
            return;
        }
        set.add(ext.replace(".", "").toLowerCase().trim());
    }

    private static void remove(HashSet set, String ext) {
        if (TextUtils.isEmpty(ext)) {
            return;
        }
        set.remove(ext.replace(".", "").toLowerCase().trim());
    }

    public boolean isMedia(String ext) {
        if (TextUtils.isEmpty(ext)) {
            return false;
        }
        if (NO_CACH.contains(ext)) {
            return true;
        }
        return noCache.contains(ext.toLowerCase().trim());
    }

    public boolean canCache(String ext) {
        if (TextUtils.isEmpty(ext)) {
            return false;
        }
        ext = ext.toLowerCase().trim();
        if (STATIC.contains(ext)) {
            return true;
        }
        return statics.contains(ext);
    }


    public CacheExtConfig addExt(String ext) {
        add(statics, ext);
        return this;
    }

    public CacheExtConfig removeExt(String ext) {
        remove(statics, ext);
        return this;
    }

    public boolean isHtml(String ext) {
        if (TextUtils.isEmpty(ext)) {
            return false;
        }
        if (ext.toLowerCase().contains("html") ||
                ext.toLowerCase().contains("htm")) {
            return true;
        }
        return false;
    }

    public void clearAll() {
        clearDiskExt();
    }

    public void clearDiskExt() {
        statics.clear();
    }
}

