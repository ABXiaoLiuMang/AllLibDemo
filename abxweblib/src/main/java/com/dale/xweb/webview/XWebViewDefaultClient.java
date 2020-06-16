package com.dale.xweb.webview;

import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

import com.just.agentweb.WebViewClient;


public class XWebViewDefaultClient extends WebViewClient {

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        if (!TextUtils.isEmpty(url)) {
            WebResourceResponse webResourceResponse = XWebViewCacheSDK.interceptRequest(url);
            if (webResourceResponse != null) {
                return webResourceResponse;
            }
        }
        return super.shouldInterceptRequest(view, url);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        if (null != request) {
            WebResourceResponse webResourceResponse = XWebViewCacheSDK.interceptRequest(request);
            if (webResourceResponse != null) {
                return webResourceResponse;
            }
        }
        return super.shouldInterceptRequest(view, request);
    }

}