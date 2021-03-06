package com.dale.livedatademo.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.dale.net.NetSdk;
import com.dale.utils.LogUtils;

public class InitializeService extends IntentService {

    private static final String ACTION_INIT = "initApplication";

    public static void start(Context context , String name) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
        intent.putExtra("name",name);
        context.startService(intent);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d("IntentService初始化","onCreate");
    }

    /**
     * 在构造函数中传入线程名字
     **/
    public InitializeService(){
        //调用父类的构造函数
        //构造函数参数=工作线程的名字
        super("InitializeService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            String name = intent.getStringExtra("name");
            LogUtils.d("IntentService初始化","onHandleIntent----"+name);
            if (ACTION_INIT.equals(action)) {
                initApplication();
            }
        }
    }

    private void initApplication() {
        initNet();
    }




    /**
     * 初始化网络库
     */
    private void initNet(){

    }





    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}

