package com.dale.talk;

import android.content.Context;
import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.dale.net.NetSdk;
import com.dale.net.bean.DataType;
import com.dale.net.bean.LiveResult;
import com.dale.net.bean.NetLiveData;
import com.dale.talk.api.Api;
import com.dale.talk.common.TalkConfig;
import com.dale.talk.entity.BaseEntity;
import com.dale.talk.entity.LoginResult;
import com.dale.utils.LogUtils;
import com.dale.utils.MMKVUtil;

import io.rong.imlib.RongIMClient;

public class IMManager {
    private static volatile IMManager instance;
    private Context context;


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
    public void connectIM(String token, final boolean getTokenOnIncorrect, final MutableLiveData<Boolean> loginResult) {
        RongIMClient.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                //刷新tonken重新连接
                if (getTokenOnIncorrect) {
                    getToken(loginResult);
                } else {
                    loginResult.postValue(false);
                }
            }

            /**
             * 连接融云成功
             */
            @Override
            public void onSuccess(String userid) {
                loginResult.postValue(true);
            }

            /**
             * 连接融云失败
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                LogUtils.e("融云链接失败 code:" + errorCode.getValue() + ", msg:" + errorCode.getMessage());
                loginResult.postValue(false);
            }
        });

    }

    /**
     * 获取用户 IM token
     * 此接口需要在登录成功后可调用，用于在 IM 提示 token 失效时刷新 token 使用
     */
    private void getToken(final MutableLiveData<Boolean> loginResult) {
        NetLiveData<BaseEntity<LoginResult>> netLiveData = new NetLiveData<>();
        Observer<LiveResult<BaseEntity<LoginResult>>> observer = new Observer<LiveResult<BaseEntity<LoginResult>>>() {
            @Override
            public void onChanged(LiveResult<BaseEntity<LoginResult>> baseEntity) {
                if(baseEntity.type == DataType.SUCCESS){
                    connectIM(baseEntity.data.getResult().getToken(), false,loginResult);
                }else if(baseEntity.type == DataType.ERROR){
                    loginResult.postValue(false);
                }
            }
        };
        netLiveData.observeForever(observer);
        NetSdk.create(Api.class)
                .getToken()
                .send(netLiveData);
    }

    /**
     * 缓存登录
     */
    private void cacheConnectIM() {
        if (RongIMClient.getInstance().getCurrentConnectionStatus() == RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED) {
            LogUtils.d("融云长连接正常");
            String userId = MMKVUtil.getString(TalkConfig.userId);
//            loginResult.postNext(userId);
            return;
        }

        String loginToken = MMKVUtil.getString(TalkConfig.token);
        if (TextUtils.isEmpty(loginToken)) {
            return;
        }

//        connectIM(loginToken, true);
    }
}
