package com.dale.talk;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.dale.net.NetSdk;
import com.dale.net.callback.OnCallBack;
import com.dale.net.exception.ErrorMessage;
import com.dale.talk.api.Api;
import com.dale.talk.common.ErrorCode;
import com.dale.talk.entity.BaseEntity;
import com.dale.talk.entity.LoginResult;
import com.dale.utils.LogUtils;

import io.rong.imlib.RongIMClient;

public class IMManager {
    private static volatile IMManager instance;
    private Context context;
    private MutableLiveData<Boolean> autologinResult = new MutableLiveData<>();

    private IMManager() {
    }

    public static IMManager getInstance() {
        if (instance == null) {
            synchronized (IMManager.class) {
                if (instance == null) {
                    instance = new IMManager();
                }
            }
        }
        return instance;
    }

    /**
     * @param context
     */
    public void init(Context context) {
        this.context = context.getApplicationContext();

        // 初始化 IM 相关缓存
//        initIMCache();

        // 调用 RongIM 初始化
        RongIMClient.init(context,"n19jmcy59f1q9");

        // 缓存连接
        cacheConnectIM();
    }

    /**
     * 连接 IM 服务
     *
     * @param token
     * @param getTokenOnIncorrect
     * @param callback
     */
    public void connectIM(String token, final boolean getTokenOnIncorrect, final OnCallBack<String> callback) {

        RongIMClient.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                //刷新tonken重新连接
                if (getTokenOnIncorrect) {
                    getToken(new OnCallBack<BaseEntity<LoginResult>>() {
                        @Override
                        public void onSuccess(BaseEntity<LoginResult> baseEntity) {
                            connectIM(baseEntity.getResult().getToken(), false, callback);
                        }

                        @Override
                        public void onError(ErrorMessage errorMessage) {
                            callback.onError(new ErrorMessage(ErrorCode.IM_ERROR.getCode(),"登录失败"));
                        }
                    });
                } else {
                    if (callback != null) {
                        callback.onError(new ErrorMessage(ErrorCode.IM_ERROR.getCode(),"登录失败"));
                    } else {
                        // do nothing
                    }
                }
            }

            /**
             * 连接融云成功
             */
            @Override
            public void onSuccess(String userid) {
                callback.onSuccess(userid);
            }

            /**
             * 连接融云失败
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                LogUtils.e("融云链接失败 code:" + errorCode.getValue() + ", msg:" + errorCode.getMessage());
                callback.onError(new ErrorMessage(ErrorCode.IM_ERROR.getCode(),"融云连接失败"));
            }
        });

    }

    /**
     * 获取用户 IM token
     * 此接口需要在登录成功后可调用，用于在 IM 提示 token 失效时刷新 token 使用
     *
     * @param callback
     */
    private void getToken(final OnCallBack<BaseEntity<LoginResult>> callback) {
        NetSdk.create(Api.class)
                .getToken()
                .send(callback);
    }

    /**
     * 缓存登录
     */
    private void cacheConnectIM() {
//        if (RongIMClient.getInstance().getCurrentConnectionStatus() == RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED) {
//            autologinResult.setValue(true);
//            return;
//        }
//
//        UserCacheInfo userCache = this.userCache.getUserCache();
//        if (userCache == null) {
//            autologinResult.setValue(false);
//            return;
//        }
//
//        String loginToken = this.userCache.getUserCache().getLoginToken();
//        if (TextUtils.isEmpty(loginToken)) {
//            autologinResult.setValue(false);
//            return;
//        }
//
//        connectIM(loginToken, true, new ResultCallback<String>() {
//            @Override
//            public void onSuccess(String s) {
//                autologinResult.setValue(true);
//            }
//
//            @Override
//            public void onFail(int errorCode) {
//                autologinResult.setValue(false);
//            }
//        });
    }
}
