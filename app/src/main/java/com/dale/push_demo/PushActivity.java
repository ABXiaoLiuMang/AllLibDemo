package com.dale.push_demo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import com.dale.App;
import com.dale.libdemo.R;
import com.dale.push.PushSdk;
import com.dale.utils.ToastUtils;
import com.dale.xutils.logintest.LoginIntercept;

public class PushActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);
        context = this;
        requestNotify();

        ToastUtils.showLong("id -->" + PushSdk.getRegistrationId());

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginDialog();
            }
        });
    }


    @LoginIntercept(content = "请登录或注册账号")
    private void showLoginDialog() {
        App.isLogin = true;
        ToastUtils.showLong("去登录");
    }


    public void requestNotify() {
        if(!NotificationManagerCompat.from(context).areNotificationsEnabled()){
            Intent localIntent = new Intent();
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                localIntent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                localIntent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
            }
            else if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                localIntent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                localIntent.putExtra("app_package", context.getPackageName());
                localIntent.putExtra("app_uid", context.getApplicationInfo().uid);
            }else {
                localIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                localIntent.addCategory(Intent.CATEGORY_DEFAULT);
                localIntent.setData(Uri.parse("package:" + context.getPackageName()));
            }
            context.startActivity(localIntent);
        }
    }
}
