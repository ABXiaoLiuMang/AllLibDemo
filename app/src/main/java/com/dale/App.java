package com.dale;


import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.bumptech.glide.Glide;
import com.dale.emoji.LQREmotionKit;
import com.dale.framework.util.ABApplication;
import com.dale.location.LocationSdk;
import com.dale.location_demo.MyOnLocationListener;
import com.dale.net.NetSdk;
import com.dale.push.PushSdk;
import com.dale.room.IDBConfig;
import com.dale.room.RoomSdk;
import com.dale.room_demo.AppDatabase;
import com.dale.stateview_demo.custom.GlobalAdapter;
import com.dale.stateview_demo.state.Gloading;
import com.dale.utils.AppUtil;
import com.dale.utils.FileUtils;
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
            initDb();
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


    private void initDb(){
//        数据库版本 1->2 user表格新增了age列
        Migration migration_1_2 = new Migration(1,2) {
            @Override
            public void migrate(@NonNull SupportSQLiteDatabase database) {
                database.execSQL("ALTER TABLE User ADD COLUMN age integer");
            }
        };

        /**
         * 数据库版本 2->3 新增book表格
         */
         Migration MIGRATION_2_3 = new Migration(2, 3) {
            @Override
            public void migrate(SupportSQLiteDatabase database) {
                database.execSQL(
                        "CREATE TABLE IF NOT EXISTS `book` (`uid` INTEGER PRIMARY KEY autoincrement, `name` TEXT , `userId` INTEGER, 'time' INTEGER)");
            }
        };

        RoomSdk.ins()
//                .setMigration(migration_1_2)
//                .setMigration(MIGRATION_2_3)
                .initSDK(this,AppDatabase.class);
    }
/****
 * https://pan.baidu.com/s/1YsosjGjup4CgM9McTwLVFg#list/path=%2F&parentPath=%2Fsharelink3232509500-50774254064225  百度网盘kotlin 教程
 *
 * https://www.jb51.net/books/695216.html
 * https://www.jb51.net/books/681218.html
 * https://www.jb51.net/books/list572_1.html
 * https://www.jb51.net/books/634928.html
 * https://www.jb51.net/books/638342.html
 * https://www.jb51.net/
 */
}


