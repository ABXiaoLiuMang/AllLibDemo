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

    //图标选择框架用到
    public static DisplayImageOptions imageLoaderOptions = new DisplayImageOptions.Builder()//
            .showImageOnLoading(R.drawable.ic_default_image)         //设置图片在下载期间显示的图片
            .showImageForEmptyUri(R.drawable.ic_default_image)       //设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(R.drawable.ic_default_image)            //设置图片加载/解码过程中错误时候显示的图片
            .cacheInMemory(true)                                //设置下载的图片是否缓存在内存中
            .cacheOnDisk(true)                                  //设置下载的图片是否缓存在SD卡中
            .build();                                           //构建完成

    public static ImageOptions xUtilsOptions = new ImageOptions.Builder()//
            .setIgnoreGif(false)                                //是否忽略GIF格式的图片
            .setImageScaleType(ImageView.ScaleType.FIT_CENTER)  //缩放模式
            .setLoadingDrawableId(R.drawable.ic_default_image)       //下载中显示的图片
            .setFailureDrawableId(R.drawable.ic_default_image)       //下载失败显示的图片
            .build();                                           //得到ImageOptions对象
}
