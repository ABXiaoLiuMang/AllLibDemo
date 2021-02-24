package com.dale.thread_demo;

import android.os.SystemClock;

import com.dale.framework.ui.ABBaseActivity;
import com.dale.libdemo.R;
import com.dale.utils.LogUtils;

import okhttp3.OkHttpClient;

public class ThreadActivity extends ABBaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_thread;
    }

    @Override
    protected void createProvider() {

    }


    @Override
    protected void initViewsAndEvents() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.connectTimeout()
           findViewById(R.id.tv_thread).setOnClickListener(v -> {
               test();
           });

//        杀人者,打虎武松是也!!
    }

    private void test(){
    }
}
