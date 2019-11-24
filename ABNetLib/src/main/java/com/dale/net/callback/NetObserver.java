package com.dale.net.callback;

import androidx.lifecycle.Observer;

import com.dale.net.bean.LiveResult;
import com.dale.net.exception.ErrorMessage;

public abstract class NetObserver<T> implements Observer<LiveResult<T>> {
    @Override
    public void onChanged(LiveResult<T> result) {
        if (result == null) {
            return;
        }
        switch (result.type) {
            case SUCCESS:
                onSuccess(result.data);
                break;
            case ERROR:
                onError(result.errorMessage);
                break;
            default:
                throw new NullPointerException("事件类型找不到");
        }
    }

    protected abstract void onSuccess(T t);

    protected abstract void onError(ErrorMessage errorMessage);
}
