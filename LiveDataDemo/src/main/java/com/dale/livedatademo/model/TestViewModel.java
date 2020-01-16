package com.dale.livedatademo.model;

import androidx.lifecycle.ViewModel;

import com.dale.livedatademo.api.Api;
import com.dale.net.NetSdk;
import com.dale.net.bean.NetLiveData;

public class TestViewModel extends ViewModel {

    public NetLiveData<String> netLiveData = new NetLiveData<>();

    public void getTestNetLiveData(){
         NetSdk.create(Api.class)
                 .getTestString()
                 .send(netLiveData);
    }

}
