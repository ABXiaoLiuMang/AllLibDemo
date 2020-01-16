package com.dale.livedatademo.model

import androidx.lifecycle.ViewModel
import com.dale.livedatademo.api.Api
import com.dale.net.NetSdk
import com.dale.net.bean.NetLiveData

class TestViewModel2 : ViewModel() {

    val netLiveData = NetLiveData<String>()

    fun getNetLiveData(){
        NetSdk.create<Api>(Api::class.java)
            .testString
            .send(netLiveData)
    }
}