package com.dale.xweb.cache;

import android.content.Context;


import com.dale.xweb.interceptor.ResourceInterceptor;

import java.io.File;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Dns;

public class CacheConfig {

    private Context context;
    private boolean debug = true;

    private File cacheFile;
    private long cacheSize = 100 * 1024 * 1024;
    private long connectTimeout = 20;
    private long readTimeout = 20;
    private CacheExtConfig cacheExtConfig;
    private CacheType cacheType = CacheType.FORCE;

    private boolean trustAllHostname = false;
    private SSLSocketFactory sslSocketFactory = null;
    private X509TrustManager x509TrustManager = null;
    private ResourceInterceptor resourceInterceptor;

    /**
     * assets 目录设置
     */
    private String assetsDir = null;
    private boolean suffixMod = false;
    private Dns dns = null;

    public Context getContext() {
        return context;
    }

    public boolean isDebug() {
        return debug;
    }

    public File getCacheFile() {
        return cacheFile;
    }

    public long getCacheSize() {
        return cacheSize;
    }

    public long getConnectTimeout() {
        return connectTimeout;
    }

    public long getReadTimeout() {
        return readTimeout;
    }

    public CacheExtConfig getCacheExtConfig() {
        return cacheExtConfig;
    }

    public CacheType getCacheType() {
        return cacheType;
    }

    public boolean isTrustAllHostname() {
        return trustAllHostname;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return sslSocketFactory;
    }

    public X509TrustManager getX509TrustManager() {
        return x509TrustManager;
    }

    public ResourceInterceptor getResourceInterceptor() {
        return resourceInterceptor;
    }

    public String getAssetsDir() {
        return assetsDir;
    }

    public boolean isSuffixMod() {
        return suffixMod;
    }

    public Dns getDns() {
        return dns;
    }

    public static class Builder {
        private Context context;
        private boolean debug = true;

        private File cacheFile;
        private long cacheSize = 100 * 1024 * 1024;
        private long connectTimeout = 20;
        private long readTimeout = 20;
        private CacheExtConfig cacheExtConfig;
        private CacheType cacheType = CacheType.FORCE;

        private boolean trustAllHostname = false;
        private SSLSocketFactory sslSocketFactory = null;
        private X509TrustManager x509TrustManager = null;
        private ResourceInterceptor resourceInterceptor;

        /**
         * assets 目录设置
         */
        private String assetsDir = null;
        private boolean suffixMod = false;
        private Dns dns = null;

        public Builder(Context context) {
            this.context = context;
            cacheFile = new File(context.getCacheDir().toString(), "XWebViewCache");
            cacheExtConfig = new CacheExtConfig();
        }

        public Builder setResourceInterceptor(ResourceInterceptor resourceInterceptor) {
            this.resourceInterceptor = resourceInterceptor;
            return this;
        }

        public Builder setTrustAllHostname() {
            this.trustAllHostname = true;
            return this;
        }

        public Builder setSSLSocketFactory(SSLSocketFactory sslSocketFactory, X509TrustManager trustManager) {
            if (sslSocketFactory != null && trustManager != null) {
                this.sslSocketFactory = sslSocketFactory;
                this.x509TrustManager = trustManager;
            }
            return this;
        }

        public Builder setCachePath(File file) {
            if (file != null) {
                this.cacheFile = file;
            }
            return this;
        }

        public Builder setCacheSize(long cacheSize) {
            if (cacheSize > 1024) {
                this.cacheSize = cacheSize;
            }
            return this;
        }

        public Builder setReadTimeoutSecond(long time) {
            if (time >= 0) {
                readTimeout = time;
            }
            return this;
        }

        public Builder setConnectTimeoutSecond(long time) {
            if (time >= 0) {
                this.connectTimeout = time;
            }
            return this;
        }

        public Builder setCacheExtConfig(CacheExtConfig cacheExtConfig) {
            if (cacheExtConfig != null) {
                this.cacheExtConfig = cacheExtConfig;
            }
            return this;
        }

        public Builder setDebug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public Builder setCacheType(CacheType cacheType) {
            this.cacheType = cacheType;
            return this;
        }

        public Builder isAssetsSuffixMod(boolean suffixMod) {
            this.suffixMod = suffixMod;
            return this;
        }

        public Builder setAssetsDir(String dir) {
            if (dir != null) {
                this.assetsDir = dir;
            }
            return this;
        }

        public void setDns(Dns dns) {
            this.dns = dns;
        }

        public CacheConfig build() {

            CacheConfig cacheConfig = new CacheConfig();
            cacheConfig.context = context;
            cacheConfig.debug = debug;
            cacheConfig.cacheFile = cacheFile;
            cacheConfig.cacheSize = cacheSize;
            cacheConfig.connectTimeout = connectTimeout;
            cacheConfig.readTimeout = readTimeout;
            cacheConfig.cacheExtConfig = cacheExtConfig;
            cacheConfig.cacheType = cacheType;
            cacheConfig.trustAllHostname = trustAllHostname;
            cacheConfig.sslSocketFactory = sslSocketFactory;
            cacheConfig.x509TrustManager = x509TrustManager;

            cacheConfig.resourceInterceptor = resourceInterceptor;

            return cacheConfig;
        }

    }

}
