package com.dale.talk;

import android.content.Context;

import io.rong.imlib.RongIMClient;

public class Test {
    public static final String DOMAIN = "http://api.sealtalk.im/";
    private void initRongIM(Context context) {
        /*
         * 如果是连接到私有云需要在此配置服务器地址
         * 如果是公有云则不需要调用此方法
         */
        //RongIM.setServerInfo("nav.cn.ronghub.com", "up.qbox.me");

        /*
         * 初始化 SDK，在整个应用程序全局，只需要调用一次。建议在 Application 继承类中调用。
         */

        /* 若直接调用init方法请在 IMLib 模块中的 AndroidManifest.xml 中, 找到 <meta-data> 中 android:name 为 RONG_CLOUD_APP_KEY的标签，
         * 将 android:value 替换为融云 IM 申请的APP KEY
         */
        //RongIM.init(this);

        // 可在初始 SDK 时直接带入融云 IM 申请的APP KEY
//        RongIM.init(context, "n19jmcy59f1q9", true);
        RongIMClient.init(context,"n19jmcy59f1q9");

    }
}
