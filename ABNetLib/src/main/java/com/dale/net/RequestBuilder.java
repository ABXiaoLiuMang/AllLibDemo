package com.dale.net;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.dale.net.bean.DataType;
import com.dale.net.bean.LiveResult;
import com.dale.net.bean.NetLiveData;
import com.dale.net.bean.ProgressModel;
import com.dale.net.callback.OnCallBack;
import com.dale.net.exception.ErrorMessage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;

public class RequestBuilder<T> implements NetCall<T> {
    public String method;
    public final ServiceMethod<T, ?> serviceMethod;
    public Object oparams;
    public String url;
    public ArrayMap<String, File> fileMap = new ArrayMap<>();
    public ArrayMap<String, String> allStringParams = new ArrayMap<>();
    public Headers.Builder headers;
    public String baseUrl;
    MediaType mMediaType;
    MutableLiveData<ProgressModel> mProgressModelLiveData;
    LifecycleOwner owner;

    RequestBuilder(ServiceMethod<T, ?> serviceMethod) {
        this.serviceMethod = serviceMethod;
        headers = new Headers.Builder();
        this.url = serviceMethod.relativeUrl;
        this.method = serviceMethod.httpMethod;
        baseUrl = NetSdk.getConfig().getBaseUrl();
        mMediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
    }

    @Override
    public NetCall<T> url(String url) {
        if (!TextUtils.isEmpty(url)) {
            if (!TextUtils.isEmpty(this.url)) {
                this.url += url;
            } else {
                this.url = url;
            }
        }
        return this;
    }

    @Override
    public NetCall<T> params(Object params) {
        this.oparams = params;
        return this;
    }

    @Override
    public NetCall<T> params(String key, File value) {
        fileMap.put(key, value);
        return this;
    }

    @Override
    public NetCall<T> params(String key, String value) {
        allStringParams.put(key, value);
        return this;
    }


    @Override
    public NetCall<T> baseUrl(String baseUrl) {
        if (TextUtils.isEmpty(baseUrl)) return this;
        this.baseUrl = baseUrl;
        return this;
    }

    @Override
    public NetCall<T> addHeader(String name, String value) {
        headers.add(name, value);
        return this;
    }

    @Override
    public NetCall<T> mediaType(MediaType mediaType) {
        this.mMediaType = mediaType;
        return this;
    }

    @Override
    public NetCall<T> send(final OnCallBack<T> baseCallBack) {
        final NetLiveData<T> netLiveData = new NetLiveData<>();
        Observer<LiveResult<T>> observer = new Observer<LiveResult<T>>() {
            @Override
            public void onChanged(LiveResult<T> t) {
                if(t.type == DataType.SUCCESS){
                    baseCallBack.onSuccess(t.data);
                }else {
                    baseCallBack.onError(new ErrorMessage(t.getErrorCode(),t.getErrorMessage()));
                }
            }
        };
        if(owner == null){
            netLiveData.observeForever(observer);
            Log.w(Constant.LOG_TAG,"请调用addLifecycleOwner以便生命周期监听");
        }else{
            netLiveData.observe(owner,observer);
        }
        send(netLiveData);
        return this;
    }

    @Override
    public NetCall<T> send(NetLiveData<T> netLiveData) {
        new Request<>(this).send(netLiveData);
        return this;
    }

    @Override
    public NetCall<T> asLife(LifecycleOwner owner) {
        this.owner = owner;
        return this;
    }

    @Override
    public RequestBuilder<T> addUploadListener( @NonNull Observer<ProgressModel> observer) {
        if (mProgressModelLiveData == null) {
            mProgressModelLiveData = new MutableLiveData<>();
        }
        if(owner == null){
            mProgressModelLiveData.observeForever(observer);
            Log.w(Constant.LOG_TAG,"请调用addLifecycleOwner以便生命周期监听");
        }else{
            mProgressModelLiveData.observe(owner, observer);
        }
        return this;
    }

}
