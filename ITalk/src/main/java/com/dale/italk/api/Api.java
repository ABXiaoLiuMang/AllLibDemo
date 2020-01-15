package com.dale.italk.api;

import com.dale.italk.entity.BaseEntity;
import com.dale.italk.entity.LoginResult;
import com.dale.net.NetCall;
import com.dale.net.http.POST;

public interface Api {

    @POST("user/login")
    NetCall<BaseEntity<LoginResult>> login();
}
