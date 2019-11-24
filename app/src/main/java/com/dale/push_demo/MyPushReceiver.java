package com.dale.push_demo;


import android.content.Context;

import com.dale.utils.LogUtils;
import com.dale.utils.ToastUtils;

import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

public class MyPushReceiver extends JPushMessageReceiver {

    /**
     * 收到自定义消息回调
     * @param context
     * @param customMessage
     */
    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        LogUtils.d("onMessage:" + customMessage.toString());
        ToastUtils.showLong(customMessage.toString());
    }

    /**
     * 点击通知回调
     * @param context
     * @param message
     */
    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage message) {
        ToastUtils.showLong(message.toString());
    }


    /**
     * 收到通知回调
     * @param context
     * @param message
     */
    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage message) {
        ToastUtils.showLong(message.toString());
        LogUtils.d("onNotifyMessageArrived:" + message.toString());
    }


    /**
     * 注册成功回调
     * @param context
     * @param registrationId
     */
    @Override
    public void onRegister(Context context, String registrationId) {
        ToastUtils.showLong(registrationId);
        LogUtils.d("onRegister  registrationId:" + registrationId);
    }


    /**
     * tag 增删查改的操作会在此方法中回调结果。
     * @param context
     * @param jPushMessage
     */
    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        int code = jPushMessage.getErrorCode();
        if(code != 0){//操作失败
        }
        super.onTagOperatorResult(context, jPushMessage);
    }


    /**
     * alias 相关的操作会在此方法中回调结果。
     * @param context
     * @param jPushMessage
     */
    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        int code = jPushMessage.getErrorCode();
        if(code != 0){//操作失败
        }
        super.onAliasOperatorResult(context, jPushMessage);
    }

}
