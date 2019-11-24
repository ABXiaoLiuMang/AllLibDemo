package com.dale.framework_demo;

import com.dale.net.bean.NetLiveData;

public class LiveDataManager {

    private static final LiveDataManager instance = new LiveDataManager();

    public static LiveDataManager getInstance() {
        return instance;
    }

    private LiveDataManager() {
    }

    public NetLiveData<String> testPrice = new NetLiveData<>();
}
