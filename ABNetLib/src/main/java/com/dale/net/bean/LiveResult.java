package com.dale.net.bean;

public class LiveResult<T>{
    public int type;
    public T data;
    public int code;
    public String message;
    public void setData(T data) {
        this.type = DataType.SUCCESS;
        this.data = data;
        this.code = 200;
        this.message = null;
    }

    public void setError(int code,String message) {
        this.type = DataType.ERROR;
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public void setLoading() {
        this.type = DataType.LOADING;
        this.code = 0;
        this.message = null;
        this.data = null;
    }

}
