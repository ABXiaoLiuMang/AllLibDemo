package com.dale.italk.presenter

import com.dale.framework.ui.BasePresenter
import com.dale.italk.TalkConfig
import com.dale.italk.contract.LoginContract
import com.dale.net.NetSdk
import com.dale.net.callback.OnCallBack
import com.dale.net.exception.ErrorMessage
import com.dale.talk.IMManager
import com.dale.talk.api.Api
import com.dale.talk.entity.BaseEntity
import com.dale.talk.entity.LoginResult
import com.dale.utils.MMKVUtil
import com.dale.utils.ToastUtils
import java.util.*

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
                    MMKVUtil.put(TalkConfig.userName, userName)
                    MMKVUtil.put(TalkConfig.passWord, passWord)
                    MMKVUtil.put(TalkConfig.country, "86")
                    MMKVUtil.put(TalkConfig.token, baseEntity.result.token)
                    connectIM(baseEntity.result)
                }

                override fun onError(errorMessage: ErrorMessage) {
                    ToastUtils.showLong(errorMessage.message)
                }
            })
    }

    private fun connectIM(loginResult: LoginResult) {
        IMManager.getInstance().connectIM(loginResult.token, true, object : OnCallBack<String> {
            override fun onSuccess(t: String?) {
                if (isAttachView) {
                    view.loginResult()
                }
            }

            override fun onError(errorMessage: ErrorMessage?) {
            }


        })
    }

}