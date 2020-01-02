package com.dale.worker_demo.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.dale.log.LogUtils;
import com.dale.worker_demo.WorkActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyWorker extends Worker {

    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
    private Date mDate = new Date();

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        int n = getInputData().getInt(WorkActivity.KEY,-1);
        LogUtils.d("开始执行任务");

        mDate.setTime(System.currentTimeMillis());

        //任务的返回值
        Data outputData = new Data.Builder()
                .putString(WorkActivity.KEY, n + "@" + mSimpleDateFormat.format(mDate) + "#" + getId())
                .build();
        return Result.success(outputData);
    }

    @Override
    public void onStopped() {
        super.onStopped();
        LogUtils.d("任务执行结束");
    }

}
