package com.dale.xweb.cache;

import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebResourceResponse;

import com.ty.xweb.interceptor.HttpCacheInterceptor;
import com.ty.xweb.util.MimeTypeMapUtils;
import com.ty.xweb.util.NetUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.ByteString;
import okio.GzipSource;
import okio.Okio;
import okio.Source;

public class OkHttpCacheClient extends CacheClient {

    private OkHttpClient httpClient = null;

    public OkHttpCacheClient(CacheConfig cacheConfig) {
        super(cacheConfig);
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    @Override
    public void initClient() {
        final Cache cache = new Cache(cacheConfig.getCacheFile(), cacheConfig.getCacheSize());
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(cacheConfig.getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(cacheConfig.getReadTimeout(), TimeUnit.SECONDS)
                .addNetworkInterceptor(new HttpCacheInterceptor());
        if (cacheConfig.isTrustAllHostname()) {
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        }
        if (cacheConfig.getSslSocketFactory() != null && cacheConfig.getX509TrustManager() != null) {
            builder.sslSocketFactory(cacheConfig.getSslSocketFactory(), cacheConfig.getX509TrustManager());
        }
        if (cacheConfig.getDns() != null) {
            builder.dns(cacheConfig.getDns());
        }
        httpClient = builder.build();
    }

    private boolean checkUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        // okhttp only deal with http[s]
        if (!url.startsWith("http")) {
            return false;
        }

        if (cacheConfig.getResourceInterceptor() != null
                && !cacheConfig.getResourceInterceptor().interceptor(url)) {
            return false;
        }

        String extension = MimeTypeMapUtils.getFileExtFromUrl(url);

        if (TextUtils.isEmpty(extension)) {
            return false;
        }
        if (cacheConfig.getCacheExtConfig() != null &&
                cacheConfig.getCacheExtConfig().isMedia(extension)) {
            return false;
        }
        if (cacheConfig.getCacheExtConfig() != null &&
                !cacheConfig.getCacheExtConfig().canCache(extension)) {
            return false;
        }
        return true;
    }

    @Override
    public InputStream getCacheFile(File path, String url) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        String key = ByteString.encodeUtf8(url.toString()).md5().hex();
        File entryFile = new File(path.getAbsolutePath(), key + "." + ENTRY_METADATA);
        File bodyFile = new File(path.getAbsolutePath(), key + "." + ENTRY_BODY);
        if (entryFile != null && entryFile.exists() && bodyFile != null && bodyFile.exists()) {
            try {
                BufferedReader fr = new BufferedReader(new FileReader(entryFile), 1024);
                String line = "";
                boolean isGzip = false;
                while ((line = fr.readLine()) != null) {
                    if (line.contains("Content-Encoding") &&
                            line.contains("gzip")) {
                        isGzip = true;
                        break;
                    }
                }
                InputStream inputStream = new FileInputStream(bodyFile);
                if (!isGzip) {
                    return inputStream;
                } else {
                    Source source = Okio.source(bodyFile);
                    source = new GzipSource(source);
                    return Okio.buffer(source).inputStream();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void addHeader(Request.Builder reqBuilder, Map<String, String> headers) {
        if (headers == null) {
            return;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            reqBuilder.addHeader(entry.getKey(), entry.getValue());
        }
    }


    @Override
    public WebResourceResponse getWebResourceResponse(String url, Map<String, String> headers) {
        if (cacheConfig.getCacheType() == CacheType.NORMAL) {
            return null;
        }
        if (!checkUrl(url)) {
            return null;
        }

//        if (mCacheConfig.isEnableAssets()) {
//            InputStream inputStream = AssetsLoader.getInstance().getResByUrl(url);
//            if (inputStream != null) {
//                CacheWebViewLog.d(String.format("from assets: %s", url), mDebug);
//                String mimeType = MimeTypeMapUtils.getMimeTypeFromUrl(url);
//                WebResourceResponse webResourceResponse = new WebResourceResponse(mimeType, "", inputStream);
//                return webResourceResponse;
//            }
//        }

        try {
            Request.Builder reqBuilder = new Request.Builder()
                    .url(url);
            String extension = MimeTypeMapUtils.getFileExtFromUrl(url);

            if (cacheConfig.getCacheExtConfig() != null &&
                    cacheConfig.getCacheExtConfig().isHtml(extension)) {
                headers.put(KEY_CACHE, cacheConfig.getCacheType().ordinal() + "");
            }
            addHeader(reqBuilder, headers);

            if (!NetUtils.isConnected(cacheConfig.getContext())) {
                reqBuilder.cacheControl(CacheControl.FORCE_CACHE);
            }
            Request request = reqBuilder.build();
            Response response = httpClient.newCall(request).execute();
            Response cacheRes = response.cacheResponse();

            String mimeType = MimeTypeMapUtils.getMimeTypeFromUrl(url);
            WebResourceResponse webResourceResponse = new WebResourceResponse(mimeType, "", response.body().byteStream());
            if (response.code() == 504 && !NetUtils.isConnected(cacheConfig.getContext())) {
                return null;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                String message = response.message();
                if (TextUtils.isEmpty(message)) {
                    message = "OK";
                }
                try {
                    webResourceResponse.setStatusCodeAndReasonPhrase(response.code(), message);
                } catch (Exception e) {
                    return null;
                }
                webResourceResponse.setResponseHeaders(NetUtils.multimapToSingle(response.headers().toMultimap()));
            }

            return webResourceResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}

