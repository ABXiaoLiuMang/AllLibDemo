package com.dale.worker_demo;

import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dale.framework.ui.ABBaseActivity;
import com.dale.libdemo.R;
import com.dale.worker_demo.util.MyWorker;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class WorkActivity extends ABBaseActivity {

    public static String KEY = "KEY";
    private static int VALUE = 1;
    private String TAG = "tag";
    TextView tv_worker_result;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_worker_demo;
    }

    @Override
    protected void createProvider() {

    }

    @Override
    protected void initViewsAndEvents() {
        tv_worker_result = findViewById(R.id.tv_worker_result);

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });



    }

    // 提交A-B  在提交C-D  BD 一起成功后，提交到E 如下下发

//    WorkContinuation chain1 = WorkManager.getInstance()
//            .beginWith(workA)
//            .then(workB);
//
//    WorkContinuation chain2 = WorkManager.getInstance()
//            .beginWith(workC)
//            .then(workD);
//
//    WorkContinuation chain3 = WorkContinuation
//            .combine(chain1, chain2)
//            .then(workE);
//chain3.enqueue();


    private void test(){
        Data myData = new Data.Builder()
                .putInt(KEY, VALUE++)
                .build();
        //约束条件
        Constraints constraints = new Constraints.Builder().setRequiresBatteryNotLow(true)
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .build();

        OneTimeWorkRequest.Builder argWorkBuilder = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setConstraints(constraints)
                .setInitialDelay(1, TimeUnit.SECONDS)
                .setInputData(myData);

        OneTimeWorkRequest request = argWorkBuilder.addTag(TAG).build();

        //取消任务
//        UUID compressionWorkId = request.getId();
//        WorkManager.getInstance(mContext).cancelWorkById(compressionWorkId);


        //查询
        LiveData<List<WorkInfo>> liveData = WorkManager.getInstance(mContext).getWorkInfosByTagLiveData(TAG);
        //观察
        liveData.observe(this, new Observer<List<WorkInfo>>() {
            @Override
            public void onChanged(List<WorkInfo> workInfos) {
                for(WorkInfo workInfo : workInfos){
                    if(workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED){
                        tv_worker_result.setText(workInfo.getOutputData().getString(KEY));
                    }
                }
            }
        });

        Operation operation = WorkManager.getInstance(mContext).enqueue(request);
    }

}
