package com.dale.thread_demo;

import android.os.SystemClock;

import com.dale.framework.ui.ABBaseActivity;
import com.dale.libdemo.R;
import com.dale.tasklib.Task;
import com.dale.tasklib.TaskCallBack;
import com.dale.utils.LogUtils;

import okhttp3.OkHttpClient;

public class ThreadActivity extends ABBaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_thread;
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
        new Task<Integer>().setCallBack(new TaskCallBack<Integer>() {

            @Override
            public Integer getResult(Object... integers) {
                LogUtils.d("-->" + Thread.currentThread().getName() + " 开始");
                SystemClock.sleep(10 * 1000);
                return 100;
            }

            @Override
            public void onSuccess(Integer integer) {
                LogUtils.d("-->" + Thread.currentThread().getName() + " r:" + integer);
            }
        }).execute();
    }
}
