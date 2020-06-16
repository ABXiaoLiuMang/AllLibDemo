package com.dale.xweb.interceptor;

import android.text.TextUtils;


import com.dale.xweb.cache.CacheType;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.dale.xweb.cache.CacheClient.KEY_CACHE;


public class HttpCacheInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        String cache = request.header(KEY_CACHE);
        Response originResponse = chain.proceed(request);

        if (!TextUtils.isEmpty(cache) && cache.equals(CacheType.NORMAL.ordinal() + "")) {
            return originResponse;
        }

        return originResponse.newBuilder().removeHeader("pragma").removeHeader("Cache-Control")
                .header("Cache-Control", "max-age=3153600000").build();

    }
}

