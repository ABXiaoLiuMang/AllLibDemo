package com.dale.thread_demo;

import android.os.SystemClock;

import com.dale.framework.ui.ABBaseActivity;
import com.dale.libdemo.R;
import com.dale.tasklib.Task;
import com.dale.tasklib.TaskCallBack;
import com.dale.utils.LogUtils;

public class ThreadActivity extends ABBaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_thread;
    }

    @Override
    protected void initPresenters() {

    }

    @Override
    protected void initViewsAndEvents() {
           findViewById(R.id.tv_thread).setOnClickListener(v -> {
               new Task<Integer>(this).setCallBack(new TaskCallBack<Integer>() {

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
           });


    }
}
