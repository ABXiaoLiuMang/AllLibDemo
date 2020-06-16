package com.dale.xweb.interceptor;

import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;

import java.io.File;
import java.io.InputStream;

public interface WebViewRequestInterceptor {

    WebResourceResponse interceptRequest(WebResourceRequest request);

    WebResourceResponse interceptRequest(String url);

    File getCachePath();

    void clearCache();

    /**
     *
     * @param url
     * @return
     */
    InputStream getCacheFile(String url);

}

