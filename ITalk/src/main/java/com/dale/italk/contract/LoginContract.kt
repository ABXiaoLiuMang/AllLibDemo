package com.dale.italk.contract

interface LoginContract {

    interface IPresenter {
        fun login(userName: String, passWord: String)

    }

    interface IView {
        fun loginResult()
    }
}