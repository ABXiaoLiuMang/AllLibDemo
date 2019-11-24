package com.dale.push;

import cn.jpush.android.api.JPushInterface;

/**
 * create by Dale
 * create on 2019/7/24
 * description:
 */
public class PushSdk {

    private static class PushSdkHolder {
        private static IPushConfig isdkConfig = new PushConfig();
    }

    public static IPushConfig ins() {
        return PushSdkHolder.isdkConfig;
    }


    public static String getRegistrationId(){
        return JPushInterface.getRegistrationID(ins().getApp());
    }
}
