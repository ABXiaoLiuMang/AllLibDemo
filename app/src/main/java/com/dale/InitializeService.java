package com.dale;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Process;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.dale.audio.AudioRecordManager;
import com.dale.emoji.LQREmotionKit;
import com.dale.net.NetSdk;
import com.dale.push.PushSdk;
import com.dale.utils.LogUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

public class InitializeService extends IntentService {

    private static final String ACTION_INIT = "initApplication";

    public static void start(Context context , String name) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
        intent.putExtra("name",name);
        context.startService(intent);
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

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d("IntentService初始化","onCreate");
    }

    private void initApplication() {
        //处理耗时操作和避免在application做过多初始化工作，比如初始化数据库等等
        LogUtils.d("IntentService初始化","initApplication");
        PushSdk.ins().initSDK(getApplication());
        initEmotion();//加载表情

        //必须先初始化
        NetSdk.config(this)
                .baseUrl("https://www.soarg999.com/CP57/")
                .needLog(true);




        //图标选择框架用到
        ImageLoaderConfiguration imageconfig = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(imageconfig);     //UniversalImageLoader初始化
        x.Ext.init(getApplication());

//        AudioRecordManager.getInstance(getApplicationContext());
        LogUtils.d("pid:" + Process.myPid() +" th:" + Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        LogUtils.d("IntentService初始化","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d("IntentService销毁","onDestroy");
    }

    private void initEmotion(){
        LQREmotionKit.init(this, (context, path, imageView) -> Glide.with(context).load(path).centerCrop().into(imageView));
    }

}

