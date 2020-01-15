package com.dale.italk.presenter

import android.util.Log
import com.dale.framework.ui.BasePresenter
import com.dale.italk.api.Api
import com.dale.italk.contract.LoginContract
import com.dale.italk.entity.BaseEntity
import com.dale.italk.entity.LoginResult
import com.dale.net.NetSdk
import com.dale.net.callback.OnCallBack
import com.dale.net.exception.ErrorMessage
import com.dale.utils.LogUtils
import com.dale.utils.ToastUtils
import java.util.HashMap

class LoginPresenter : BasePresenter<LoginContract.IView>(), LoginContract.IPresenter {

    override fun login(userName: String, passWord: String) {
        val paramsMap = HashMap<String, String>()
        paramsMap["region"] = "86"
        paramsMap["phone"] = userName
        paramsMap["password"] = passWord
        NetSdk.create<Api>(Api::class.java)
            .login()
            .asLife(lifecycleOwner)
            .params(paramsMap)
            .send(object : OnCallBack<BaseEntity<LoginResult>> {
                override fun onSuccess(baseEntity: BaseEntity<LoginResult>) {
                    if (isAttachView) {
                        view.loginResult()
                    }
                    LogUtils.d("d:" + baseEntity.result.token)
                }

                override fun onError(errorMessage: ErrorMessage) {
                    ToastUtils.showLong(errorMessage.message)
                }
            })
    }

}