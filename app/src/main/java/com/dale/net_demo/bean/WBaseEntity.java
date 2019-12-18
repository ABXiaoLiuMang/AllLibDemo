package com.dale.net_demo.bean;

import com.dale.net.callback.XEntity;
import com.dale.utils.LogUtils;

public class WBaseEntity<T> implements XEntity {
    private int code;
    private T result;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isOk() {
        return code == 200;
    }

    @Override
    public void error() {
        LogUtils.d("模拟登陆失效 code:" + code);
    }


}

