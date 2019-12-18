package com.dale.net_demo;

import com.dale.net.NetCall;
import com.dale.net.http.GET;
import com.dale.net.http.POST;
import com.dale.net_demo.bean.BaseEntity;
import com.dale.net_demo.bean.ListBean;
import com.dale.net_demo.bean.MyData;
import com.dale.net_demo.bean.TokenData;
import com.dale.net_demo.bean.WBaseEntity;

import java.util.List;

/**
 * create by Dale
 * create on 2019/7/12
 * description:
 */
public interface Api {

    @GET("api/v1/config_v2")
//    NetCall<BaseEntity<String>> getModelString();
    NetCall<BaseEntity<MyData>> getModelString();
//    NetCall<AppConfigBean> getModelString();

    @GET()
    NetCall<WBaseEntity<List<ListBean>>> getListDemo();


    @GET("api/auth/v1/msg_list")
    NetCall<String> getMsgList();

    @POST("api/v1/authtoken")
    NetCall<TokenData> getUserLoginInfo();
//    NetCall<LoginTokenEntity> getUserLoginInfo();
}
