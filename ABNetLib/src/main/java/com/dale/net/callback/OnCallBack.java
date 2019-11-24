package com.dale.net.callback;

import com.dale.net.exception.ErrorMessage;

/**
 * create by Dale
 * create on 2019/7/14
 * description: 网络请求回调
 */
public interface OnCallBack<T>{

     void onSuccess(T t);

     void onError(ErrorMessage errorMessage);
}
