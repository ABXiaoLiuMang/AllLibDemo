package com.dale.net_demo;

import com.dale.net.NetCall;
import com.dale.net.http.GET;
import com.dale.net.http.POST;
import com.dale.net_demo.bean.MyData;
import com.dale.net_demo.bean.TokenData;

/**
 * create by Dale
 * create on 2019/7/12
 * description:
 */
public interface Api {

    @GET("api/v1/config_v2")
//    NetCall<BaseEntity<String>> getModelString();
    NetCall<MyData> getModelString();
//    NetCall<AppConfigBean> getModelString();

    @GET("api/auth/v1/msg_list")
    NetCall<String> getMsgList();

    @POST("api/v1/authtoken")
    NetCall<TokenData> getUserLoginInfo();
//    NetCall<LoginTokenEntity> getUserLoginInfo();
}
