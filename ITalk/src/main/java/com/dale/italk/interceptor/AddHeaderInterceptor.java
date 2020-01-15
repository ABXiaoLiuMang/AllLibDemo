package com.dale.italk.interceptor;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加header包含cookie拦截器
 */
public class AddHeaderInterceptor implements Interceptor {
    private Context mContext;

    public AddHeaderInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        SharedPreferences preferences = mContext.getSharedPreferences(NetConstant.API_SP_NAME_NET,
                Context.MODE_PRIVATE);

        //添加cookie
        HashSet<String> cookieSet = (HashSet<String>) preferences.getStringSet(NetConstant.API_SP_KEY_NET_COOKIE_SET, null);
        if (cookieSet != null) {
            for (String cookie : cookieSet) {
                builder.addHeader("Cookie", cookie);
            }
        }

        //添加用户登录认证
        String auth = preferences.getString(NetConstant.API_SP_KEY_NET_HEADER_AUTH, null);
        if (auth != null) {
            builder.addHeader("Authorization", auth);
        }

        return chain.proceed(builder.build());
    }
}
