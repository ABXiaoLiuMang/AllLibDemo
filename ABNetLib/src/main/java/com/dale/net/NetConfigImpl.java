package com.dale.net;

import com.dale.net.manager.NetConfig;
import com.dale.net.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;

/**
 * create by Dale
 * create on 2019/7/12
 * description:
 */
public class NetConfigImpl implements NetConfig {

    private int connectTimeout = 10000;
    private int readTimeout = 10000;
    private int writeTimeout = 10000;
    private Map<String,String> headers = new HashMap<>();
    private Map<String,String> paramsMap = new HashMap<>();
    private List<Interceptor> interceptors = new ArrayList<>();
    private String baseUrl;
    private boolean needLog = false;
    private String moduleKey = "defaultModuleKey";

    @Override
    public NetConfig needLog(boolean allowLog) {
        this.needLog = allowLog;
        return this;
    }

    @Override
    public NetConfig connectTimeout(int time) {
        connectTimeout = time;
        return this;
    }

    @Override
    public NetConfig readTimeout(int time) {
        readTimeout = time;
        return this;
    }

    @Override
    public NetConfig writeTimeout(int time) {
        writeTimeout = time;
        return this;
    }

    @Override
    public NetConfig addHeader(String name, String value) {
        this.headers.put(name,value);
        return this;
    }

    @Override
    public NetConfig params(String key, String value) {
        this.paramsMap.put(key,value);
        return this;
    }

    @Override
    public NetConfig baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    @Override
    public NetConfig setModuleClass(Class cls) {
        this.moduleKey = cls.getName();
        return this;
    }

    @Override
    public NetConfig addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
        return this;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getParamsMap() {
        return paramsMap;
    }

    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public boolean isNeedLog() {
        return needLog;
    }

    public String getModuleKey() {
        return moduleKey;
    }
}
