package com.dale.italk.interceptor;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * 接受cookie拦截器
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    private Context mContext;

    public ReceivedCookiesInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookiesSet = new HashSet<>(originalResponse.headers("Set-Cookie"));

            SharedPreferences.Editor config = mContext.getSharedPreferences(NetConstant.API_SP_NAME_NET, MODE_PRIVATE)
                    .edit();
            config.putStringSet(NetConstant.API_SP_KEY_NET_COOKIE_SET, cookiesSet);
            config.apply();
        }

        return originalResponse;
    }
}
