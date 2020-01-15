package com.dale.talk.entity;

import com.dale.net.callback.XEntity;
import com.dale.utils.ToastUtils;

public class BaseEntity<T> implements XEntity {
    private int code;
    private T result;
    private String msg = "请求失败";


    public int getCode() {
        return code;
    }

    public T getResult() {
        return result;
    }

    @Override
    public boolean isOk() {
        return code == 200;
    }

    @Override
    public void error() {
        ToastUtils.showLong(msg);
    }

    public String getMessage() {
        return msg;
    }

}

