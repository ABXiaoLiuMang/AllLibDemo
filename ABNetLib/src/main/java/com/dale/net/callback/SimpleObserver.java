package com.dale.net.callback;


import com.dale.utils.ToastUtils;

public abstract class SimpleObserver<T> extends NetObserver<T>{

    @Override
    protected void onError(int code, String errMsg) {
        ToastUtils.showLong(errMsg);
    }

}
