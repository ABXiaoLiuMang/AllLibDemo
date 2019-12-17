package com.dale.tasklib;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;

import com.dale.utils.LogUtils;

public class Task<Result> implements Runnable, LifecycleObserver {

    private Object[] params;
    private MutableLiveData<Result> liveData;
    private TaskCallBack<Result> callBack;
    private boolean isBack = true;

    public Task() {
//        owner.getLifecycle().addObserver(this);
        liveData = new MutableLiveData<>();
        Observer<Result> observer = new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                callBack.onSuccess(result);
            }
        };
//        liveData.observe(owner, observer);
    }

    @Override
    public void run() {
        if (callBack != null && liveData != null) {
//            Result result = callBack.getResult(params);
//            if(isBack){
//                liveData.postValue(result);
//            }else {
//                LogUtils.d("不执行了");
//            }

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

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void cancel(){
        isBack = false;
    }

}
