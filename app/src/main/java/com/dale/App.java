package com.dale;


import android.content.Context;
import android.widget.ImageView;

import androidx.multidex.MultiDex;

import com.dale.framework_demo.api.ApiService;
import com.dale.framework.util.ABApplication;
import com.dale.libdemo.R;
import com.dale.location.LocationSdk;
import com.dale.location_demo.MyOnLocationListener;
import com.dale.net.NetSdk;
import com.dale.net.manager.NetConfig;
import com.dale.push.PushSdk;
import com.dale.utils.LogUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * create by Dale
 * create on 2019/7/24
 * description:
 */
public class App extends ABApplication {

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(com.dale.framework.R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
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

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);


        LocationSdk.ins().setAllowFirst(true).setOnLocationListener(new MyOnLocationListener()).initSDK(this);

        PushSdk.ins().initSDK(this);


        NetConfig config = NetSdk.config(this)
                .baseUrl(ApiService.API_HOST)
                .setModuleClass(ApiService.class)
                .needLog(true);
        NetSdk.initSdk(config);



        //图标选择框架用到
        ImageLoaderConfiguration imageconfig = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(imageconfig);     //UniversalImageLoader初始化
        x.Ext.init(this);                           //xUtils3初始化
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}


