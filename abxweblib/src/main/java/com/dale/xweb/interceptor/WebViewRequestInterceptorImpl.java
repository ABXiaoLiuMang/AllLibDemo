package com.dale.xweb.interceptor;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.URLUtil;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;

import com.dale.xweb.cache.CacheClient;
import com.dale.xweb.cache.CacheConfig;
import com.dale.xweb.cache.OkHttpCacheClient;
import com.dale.xweb.util.FileUtils;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class WebViewRequestInterceptorImpl implements WebViewRequestInterceptor {

    private CacheConfig mCacheConfig;
    private CacheClient mCacheClient = null;
    private String mOrigin = "";
    private String mReferer = "";
    private String mUserAgent = "";


    public WebViewRequestInterceptorImpl(CacheConfig cacheConfig) {
        this.mCacheConfig = cacheConfig;
        initCacheClient();
    }

    private void initCacheClient() {
        mCacheClient = new OkHttpCacheClient(mCacheConfig);
        mCacheClient.initClient();
    }


    private Map<String, String> buildHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        if (!TextUtils.isEmpty(mOrigin)) {
            headers.put("Origin", mOrigin);
        }
        if (!TextUtils.isEmpty(mReferer)) {
            headers.put("Referer", mReferer);
        }
        if (!TextUtils.isEmpty(mUserAgent)) {
            headers.put("User-Agent", mUserAgent);
        }
        return headers;
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public WebResourceResponse interceptRequest(WebResourceRequest request) {
        return interceptRequest(request.getUrl().toString(), request.getRequestHeaders());
    }

    @Override
    public WebResourceResponse interceptRequest(String url) {
        return interceptRequest(url, buildHeaders());
    }

    private WebResourceResponse interceptRequest(String url, Map<String, String> headers) {
        return mCacheClient.getWebResourceResponse(url, headers);
    }

    @Override
    public File getCachePath() {
        return mCacheConfig.getCacheFile();
    }

    @Override
    public void clearCache() {
        FileUtils.deleteDirs(mCacheConfig.getCacheFile().getAbsolutePath(), false);
    }

    @Override
    public InputStream getCacheFile(String url) {
        return mCacheClient.getCacheFile(mCacheConfig.getCacheFile(), url);
    }

    public boolean isValidUrl(String url) {
        return URLUtil.isValidUrl(url);
    }

}
