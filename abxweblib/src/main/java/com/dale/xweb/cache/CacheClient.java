package com.dale.xweb.cache;

import android.webkit.WebResourceResponse;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

public abstract class CacheClient {

    public static final String KEY_CACHE = "WebResourceInterceptor-Key-Cache";
    public static final int ENTRY_METADATA = 0;
    public static final int ENTRY_BODY = 1;

    /**
     * 缓存配置信息
     */
    protected CacheConfig cacheConfig;

    public CacheClient(CacheConfig cacheConfig) {
        this.cacheConfig = cacheConfig;
    }

    /**
     * 初始化
     */
    public abstract void initClient();

    /**
     * 获取缓存文件
     *
     * @param path
     * @param url
     * @return
     */
    public abstract InputStream getCacheFile(File path, String url);

    /**
     * 获取缓存资源
     *
     * @param url
     * @param headers
     * @return
     */
    public abstract WebResourceResponse getWebResourceResponse(String url, Map<String, String> headers);
}

