package com.dale.location;


import android.app.Application;


public interface ILocationConfig {

    void initSDK(Application app);

    ILocationConfig setOnLocationListener(OnLocationListener onLocationListener);

    /**
     *
     * @param allow true:第一次定位成功后，就关闭 false: 第一次地位成功后，
     *              不关闭，位置有变化就会收到更新
     * @return
     */
    ILocationConfig setAllowFirst(boolean allow);

    void startLocation();

}
