package com.dale.italk.model

import androidx.lifecycle.ViewModel
import com.dale.net.NetSdk
import com.dale.net.bean.NetLiveData
import com.dale.net.exception.ErrorMessage
import com.dale.talk.IMManager
import com.dale.talk.api.Api
import com.dale.talk.entity.BaseEntity
import com.dale.talk.entity.LoginResult
import java.util.*

class LoginViewModel : ViewModel() {

     var loginLiveData = NetLiveData<BaseEntity<LoginResult>>()//登录

     fun login(userName: String, passWord: String) {
        val paramsMap = HashMap<String, String>()
        paramsMap["region"] = "86"
        paramsMap["phone"] = userName
        paramsMap["password"] = passWord
        NetSdk.create<Api>(Api::class.java)
            .login()
            .params(paramsMap)
            .send(loginLiveData)
    }

    /**
     * 连接融云
     */
    fun connectIM(loginResult: LoginResult, tokenOnIncorrect :Boolean) {
        IMManager.getInstance().connectIM(loginResult.token, tokenOnIncorrect)
    }

}