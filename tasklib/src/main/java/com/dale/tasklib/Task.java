package com.dale.tasklib;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class Task<Result> implements Runnable {

    private Object[] params;
    private MutableLiveData<Result> liveData;
    private TaskCallBack<Result> callBack;

    public Task(LifecycleOwner owner) {
        liveData = new MutableLiveData<>();
        Observer<Result> observer = new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                callBack.onSuccess(result);
            }
        };
        liveData.observe(owner, observer);
    }

    @Override
    public void run() {
        if (callBack != null && liveData != null) {
            Result result = callBack.getResult(params);
            liveData.postValue(result);
        }
    }

    public Task setCallBack(TaskCallBack<Result> callBack) {
        this.callBack = callBack;
        return this;
    }


    public void execute(Object... params) {
        this.params = params;
        ThreadPoolManger.getInstance().execute(this);
    }

}
