package com.dale.net_demo.bean;

import com.dale.net.callback.XEntity;
import com.dale.utils.LogUtils;

public class BaseEntity<T> implements XEntity {
    private int status;
    private T data;
    private String msg;

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    @Override
    public boolean isOk() {
        return status == 1;
    }

    @Override
    public void error() {
        LogUtils.d("模拟登陆失效 code:" + status);
    }

    public String getMessage() {
        return msg;
    }

}

