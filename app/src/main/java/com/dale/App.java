package com.dale;


import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

import androidx.multidex.MultiDex;

import com.bumptech.glide.Glide;
import com.dale.emoji.LQREmotionKit;
import com.dale.framework.util.ABApplication;
import com.dale.location.LocationSdk;
import com.dale.location_demo.MyOnLocationListener;
import com.dale.net.NetSdk;
import com.dale.push.PushSdk;
import com.dale.stateview_demo.custom.GlobalAdapter;
import com.dale.stateview_demo.state.Gloading;
import com.dale.utils.LogUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.zbj.videoplayer.utils.VideoLogUtil;

import org.xutils.x;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * create by Dale
 * create on 2019/7/24
 * description:
 */
public class App extends ABApplication {

    public static boolean isLogin = false;

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
//            layout.setPrimaryColorsId(com.dale.framework.R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
            return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();

        LogUtils.isDebug(true);
        VideoLogUtil.setIsLog(true);

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);


        if(isMainProcess()){
            LogUtils.d("main pid:" + Process.myPid() +" th:" + Thread.currentThread().getName());
            LocationSdk.ins().setAllowFirst(true).setOnLocationListener(new MyOnLocationListener()).initSDK(this);
            InitializeService.start(this,"初始化放在线程中");
                      //xUtils3初始化
        }


        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(true) // 实际场景建议.debug(BuildConfig.DEBUG)
                /**
                 * 可以获取到{@link me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning}
                 * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                 */
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();

        Gloading.initDefault(new GlobalAdapter());
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    //是否是主进程
    private boolean isMainProcess() {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                LogUtils.d(String.format("当前进程：%s", appProcess.processName) );
                return getApplicationInfo().packageName.equals(appProcess.processName);
            }
        }
        return false;
    }


}


