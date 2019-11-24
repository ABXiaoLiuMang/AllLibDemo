package com.dale.push;


import android.app.Application;


public interface IPushConfig {

    void initSDK(Application app);

    Application getApp();

}
