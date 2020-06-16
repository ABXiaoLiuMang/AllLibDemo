package com.dale.xweb.util;

import android.text.TextUtils;
import android.webkit.MimeTypeMap;

public class MimeTypeMapUtils {

    /**
     *
     * @param url
     * @return
     */
    public static String getFileExtFromUrl(String url) {
        url = url.toLowerCase();
        if (!TextUtils.isEmpty(url)) {
            int fragment = url.lastIndexOf('#');
            if (fragment > 0) {
                url = url.substring(0, fragment);
            }

            int query = url.lastIndexOf('?');
            if (query > 0) {
                url = url.substring(0, query);
            }

            int filenamePos = url.lastIndexOf('/');
            String filename =
                    0 <= filenamePos ? url.substring(filenamePos + 1) : url;

            if (!filename.isEmpty()) {
                int dotPos = filename.lastIndexOf('.');
                if (0 <= dotPos) {
                    return filename.substring(dotPos + 1);
                }
            }
        }

        return "";
    }

    /**
     *
     * @param url
     * @return
     */
    public static String getMimeTypeFromUrl(String url) {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(getFileExtFromUrl(url));
    }

    /**
     *
     * @param extension
     * @return
     */
    public static String getMimeTypeFromExtension(String extension) {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }

}
