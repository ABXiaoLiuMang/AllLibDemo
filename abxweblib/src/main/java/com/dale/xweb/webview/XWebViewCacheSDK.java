package com.dale.xweb.webview;

import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;

import com.dale.xweb.cache.CacheConfig;
import com.dale.xweb.interceptor.WebViewRequestInterceptor;
import com.dale.xweb.interceptor.WebViewRequestInterceptorImpl;

import java.io.InputStream;

public class XWebViewCacheSDK {
    private static WebViewRequestInterceptor requestInterceptor;

    public static void init(CacheConfig cacheConfig) {
        if (cacheConfig == null) {
            throw new RuntimeException("cacheConfig 不可以为 null");
        }
        requestInterceptor = new WebViewRequestInterceptorImpl(cacheConfig);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static WebResourceResponse interceptRequest(WebResourceRequest request) {
        if (requestInterceptor != null) {
            return requestInterceptor.interceptRequest(request);
        }
        return null;
    }

    public static WebResourceResponse interceptRequest(String url) {
        if (requestInterceptor != null) {
            return requestInterceptor.interceptRequest(url);
        }
        return null;
    }

    public static InputStream getCacheFile(String url) {
        if (requestInterceptor != null) {
            return requestInterceptor.getCacheFile(url);
        }
        return null;
    }

}
