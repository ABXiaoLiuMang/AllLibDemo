package com.dale.net;


import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.dale.net.bean.NetLiveData;
import com.dale.net.bean.ProgressModel;
import com.dale.net.callback.OnCallBack;

import java.io.File;

import okhttp3.MediaType;

public interface NetCall<T> {

    /**
     * 往url之后继续拼接路径使用,可以重复调用,
     */
    NetCall<T> url(String url);

    /**
     * application/json 的方式发送post请求
     * @param params 需要发送的实体类
     */
    NetCall<T> params(Object params);

    /**
     * 发送文件,可以重读调用继续添加数据
     * @param key key
     * @param value 发送的file
     */
    NetCall<T> params(String key, File value);

    /**
     * 使用key-value的方式请求网络数据,可以重读调用继续添加数据
     * @param key 请求的key
     * @param value 请求的value
     */
    NetCall<T> params(String key, String value);


    /**
     * 为这个请求单独配置baseUrl.
     */
    NetCall<T> baseUrl(String baseUrl);

    /**
     * desc: 添加header
     *
     */
    NetCall<T> addHeader(String name, String value);

    /**
     * 为请求单独设置mediaType
     * @param mediaType mediaType
     */
    NetCall<T> mediaType(MediaType mediaType);

    /**
     * 为请求设置生命周期
     * @param owner owner
     */
    NetCall<T> asLife(LifecycleOwner owner);

    /**
     * @param baseCallBack baseCallBack
     */
    NetCall<T> send(OnCallBack<T> baseCallBack);

    /**
     * @param netLiveData netLiveData
     */
    NetCall<T> send(NetLiveData<T> netLiveData);

    /**
     * 监听上传进度
     * @param observer
     * @return
     */
    NetCall<T> addUploadListener(Observer<ProgressModel> observer);
}
