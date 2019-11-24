package com.dale.net.bean;

import com.dale.net.exception.ErrorMessage;

public class LiveResult<T>{
    public DataType type;
    public T data;
    public ErrorMessage errorMessage;
    public void setData(T data) {
        this.type = DataType.SUCCESS;
        this.data = data;
        this.errorMessage = null;
    }

    public void setError(ErrorMessage errorMessage) {
        this.type = DataType.ERROR;
        this.errorMessage = errorMessage;
        this.data = null;
    }

    public String getErrorMessage() {
        return errorMessage.getMessage();
    }

    public int getErrorCode() {
        return errorMessage.getCode();
    }

}
