package com.dale.talk;

import android.content.Context;
import android.text.TextUtils;

import com.dale.net.NetSdk;
import com.dale.net.bean.NetLiveData;
import com.dale.net.exception.ErrorMessage;
import com.dale.talk.api.Api;
import com.dale.talk.common.ErrorCode;
import com.dale.talk.common.TalkConfig;
import com.dale.talk.entity.BaseEntity;
import com.dale.talk.entity.LoginResult;
import com.dale.utils.LogUtils;
import com.dale.utils.MMKVUtil;

import io.rong.imlib.RongIMClient;

public class IMManager {
    private static volatile IMManager instance;
    private Context context;
    public NetLiveData<String> loginResult = new NetLiveData<>();//登录
    public NetLiveData<BaseEntity<LoginResult>> tokenLiveData = new NetLiveData<>();//获取token

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
     */
    public void connectIM(String token, final boolean getTokenOnIncorrect) {
        RongIMClient.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                //刷新tonken重新连接
                if (getTokenOnIncorrect) {
                    getToken(tokenLiveData);
                } else {
                    loginResult.postError(new ErrorMessage(ErrorCode.IM_ERROR.getCode(),"融云连接失败"));
                }
            }

            /**
             * 连接融云成功
             */
            @Override
            public void onSuccess(String userid) {
                loginResult.postNext(userid);
            }

            /**
             * 连接融云失败
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                LogUtils.e("融云链接失败 code:" + errorCode.getValue() + ", msg:" + errorCode.getMessage());
                loginResult.postError(new ErrorMessage(ErrorCode.IM_ERROR.getCode(),"融云连接失败"));
            }
        });

    }

    /**
     * 获取用户 IM token
     * 此接口需要在登录成功后可调用，用于在 IM 提示 token 失效时刷新 token 使用
     */
    private void getToken(NetLiveData<BaseEntity<LoginResult>> netLiveData) {
        NetSdk.create(Api.class)
                .getToken()
                .send(netLiveData);
    }

    /**
     * 缓存登录
     */
    private void cacheConnectIM() {
        if (RongIMClient.getInstance().getCurrentConnectionStatus() == RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED) {
            String userId = MMKVUtil.getString(TalkConfig.userId);
            loginResult.postNext(userId);
            return;
        }

        String loginToken = MMKVUtil.getString(TalkConfig.token);
        if (TextUtils.isEmpty(loginToken)) {
            return;
        }

        connectIM(loginToken, true);
    }
}
