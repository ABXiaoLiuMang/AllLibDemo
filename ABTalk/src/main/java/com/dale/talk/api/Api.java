package com.dale.talk.api;


import com.dale.net.NetCall;
import com.dale.net.http.GET;
import com.dale.net.http.POST;
import com.dale.talk.entity.BaseEntity;
import com.dale.talk.entity.LoginResult;

public interface Api {

    //登录
    @POST("user/login")
    NetCall<BaseEntity<LoginResult>> login();

    //获取token
    @GET("user/get_token")
    NetCall<BaseEntity<LoginResult>> getToken();


}
