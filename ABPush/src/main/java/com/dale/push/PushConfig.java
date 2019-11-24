package com.dale.push;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * create by Dale
 * create on 2019/7/24
 * description:
 */
public class PushConfig implements IPushConfig {

    private Application app;

    @Override
    public void initSDK(Application app) {
        this.app = app;
        JPushInterface.init(app);
    }

    @Override
    public Application getApp() {
        return app;
    }


}
