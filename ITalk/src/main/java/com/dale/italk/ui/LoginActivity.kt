package com.dale.italk.ui

import com.dale.framework.ui.ABBaseActivity
import com.dale.italk.R
import com.dale.italk.contract.LoginContract
import com.dale.italk.presenter.LoginPresenter
import com.dale.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : ABBaseActivity<LoginPresenter>(),LoginContract.IView {


    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initViewsAndEvents() {
        login_commit.setOnClickListener { presenter.login("13608079349","woaiqin2004") }
//        login_commit.setOnClickListener { presenter.login(login_userName.text.toString(),login_passWord.text.toString()) }
    }

    override fun loginResult() {
        goActivity(MainActivity::class.java)
        finish()
    }

}