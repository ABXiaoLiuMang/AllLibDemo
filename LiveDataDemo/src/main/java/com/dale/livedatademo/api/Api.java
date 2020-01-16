package com.dale.livedatademo.api;

import com.dale.net.NetCall;
import com.dale.net.http.GET;

public interface Api {

    @GET("api/v1/config_v2")
    NetCall<String> getTestString();

}
