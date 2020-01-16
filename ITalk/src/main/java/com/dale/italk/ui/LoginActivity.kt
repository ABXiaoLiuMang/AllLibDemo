package com.dale.italk.ui

import androidx.lifecycle.ViewModelProvider
import com.dale.framework.ui.ABBaseActivity
import com.dale.italk.R
import com.dale.italk.model.LoginViewModel
import com.dale.net.callback.NetObserver
import com.dale.net.exception.ErrorMessage
import com.dale.talk.IMManager
import com.dale.talk.common.TalkConfig
import com.dale.talk.entity.BaseEntity
import com.dale.talk.entity.LoginResult
import com.dale.utils.LogUtils
import com.dale.utils.MMKVUtil
import com.dale.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : ABBaseActivity() {

    private var loginViewModel: LoginViewModel? = null
    private var userName: String? = null
    private var passWord: String? = null
    override fun createProvider() {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initViewsAndEvents() {
        login_commit.setOnClickListener {
            userName = login_userName.text.toString()
            passWord = login_passWord.text.toString()
            loginViewModel?.login(userName!!, passWord!!) }

        loginViewModel?.loginLiveData?.observe(this, object : NetObserver<BaseEntity<LoginResult>>() {
            override fun onSuccess(baseEntity: BaseEntity<LoginResult>?) {
                baseEntity?.result?.let {
                    MMKVUtil.put(TalkConfig.userName, userName)
                    MMKVUtil.put(TalkConfig.passWord, passWord)
                    MMKVUtil.put(TalkConfig.country, "86")
                    MMKVUtil.put(TalkConfig.token, it.token)
                    loginViewModel?.connectIM(it, true)
                }
            }

            override fun onLoading(show: Boolean) {
                if(show) showProgressDialog()
            }

            override fun onError(errorMessage: ErrorMessage?) {
                dismissProgressDialog()
                ToastUtils.showLong(errorMessage?.message)
            }
        })

        IMManager.getInstance().tokenLiveData?.observe(this, object : NetObserver<BaseEntity<LoginResult>>() {
            override fun onSuccess(baseEntity: BaseEntity<LoginResult>?) {
                baseEntity?.result?.let {
                    loginViewModel?.connectIM(it, false)
                }

            }

            override fun onError(errorMessage: ErrorMessage?) {
                ToastUtils.showLong(errorMessage?.message)
                dismissProgressDialog()
            }
        })

        IMManager.getInstance().loginResult.observe(this,object : NetObserver<String>(){
            override fun onSuccess(userId: String?) {
                LogUtils.d("userid:$userId")
                MMKVUtil.put(TalkConfig.userId, userId)
                dismissProgressDialog()
                goActivity(MainActivity::class.java)
                finish()
            }

            override fun onError(errorMessage: ErrorMessage?) {
                dismissProgressDialog()
                ToastUtils.showLong(errorMessage?.message)
            }

        })




    }

}