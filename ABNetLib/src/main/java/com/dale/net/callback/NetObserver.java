package com.dale.net.callback;

import androidx.lifecycle.Observer;

import com.dale.net.bean.DataType;
import com.dale.net.bean.LiveResult;

public abstract class NetObserver<T> implements Observer<LiveResult<T>> {
    @Override
    public void onChanged(LiveResult<T> result) {
        if (result == null) {
            return;
        }
        switch (result.type) {
            case DataType.SUCCESS:
                onLoading(false);
                onSuccess(result.data);
                break;
            case DataType.LOADING:
                onLoading(true);
                break;
            case DataType.ERROR:
                onLoading(false);
                onError(result.code,result.message);
                break;
            default:
                throw new NullPointerException("事件类型找不到");
        }
    }

    protected abstract void onSuccess(T t);

    protected void onLoading(boolean show){
    }

    protected abstract void onError(int code,String message);
}
