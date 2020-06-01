package com.dale.kotlin.viewmodel;

import androidx.lifecycle.ViewModel
import com.ty.bwagent.api.Api
import com.ty.bwagent.bean.BaseEntity
import com.ty.net.NetSdk
import com.ty.net.bean.NetLiveData

class TestViewModel : ViewModel() {
    var mLiveData = NetLiveData<BaseEntity<String>>()

    fun test() {
        NetSdk.create(Api::class.java)
            .getPreInfo()
            .asJSONType()
            .send(mLiveData)
    }

}