package com.dale.net;

import android.util.Log;

import com.dale.net.bean.NetLiveData;
import com.dale.net.callback.XEntity;
import com.dale.net.utils.JsonUtils;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.dale.net.Constant.DATA_PARSE_ERROR;
import static com.dale.net.Constant.REQUEST_ERROR_CODE;
import static com.dale.net.Constant.UNKNOW_SERVICE_ERROR;

/**
 * create by Dale
 * create on 2019/7/12
 * description:
 */
public class RequestCallback<T> implements Callback {

    NetLiveData<T> netLiveData;
    Request request;

    public RequestCallback(Request request, NetLiveData<T> netLiveData){
        this.request = request;
        this.netLiveData = netLiveData;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Log.d(Constant.LOG_TAG,e.getMessage());
        createErrorMessage(REQUEST_ERROR_CODE,e.getMessage());
    }

    @Override
    public void onResponse(Call call, Response response)  {
        if(response != null){
            if (response.isSuccessful()) {
                try {
                    String result = response.body().string();
                    Type respType = request.requestBuilder.serviceMethod.requestAdapter.responseType();
                    T t;
                    if(respType == String.class){
                        t = (T) result;
                    }else {
                        t = JsonUtils.fromJson(result, respType);
                    }
                    if(t == null){
                        createErrorMessage(DATA_PARSE_ERROR,"数据解析异常");
                    }else {
                        if(t instanceof XEntity){
                            XEntity xEntity = (XEntity) t;
                            if(xEntity.isOk()){
                                netLiveData.postNext(t);
                            }else {
                                createErrorMessage(-1,"自定义异常");
                                xEntity.error();
                            }
                        }else {
                            netLiveData.postNext(t);
                        }
                    }
                } catch (Exception e) {
                    createErrorMessage(UNKNOW_SERVICE_ERROR,e.getMessage());
                    e.printStackTrace();
                }

            }else{
                Log.d(Constant.LOG_TAG,"请求失败 : code:" + response.code());
                createErrorMessage(response.code(), "请求失败");
            }
        }else{
            Log.d(Constant.LOG_TAG,"请求失败 :response body 为空");
            createErrorMessage(Constant.RESPONSE_BODY_EMPTY,"response body 为空");
        }
    }

    private void createErrorMessage(int code, String message) {
        netLiveData.postError(code, message);
    }
}
