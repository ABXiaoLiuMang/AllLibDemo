package com.dale.framework_demo.ui;

import android.os.Handler;

import androidx.lifecycle.LifecycleOwner;

import com.dale.framework_demo.Person;
import com.dale.framework.ui.BasePresenter;
import com.dale.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;


public class MainPresenter extends BasePresenter<MainContract.IView> implements MainContract.IPresenter {

    int postion = 0;

    public MainPresenter(LifecycleOwner lifecycleOwner, MainContract.IView iView) {
        super(lifecycleOwner, iView);
    }

    public void onCreate(){
        super.onCreate();
//        initRequest();
        LogUtils.d("onCreate MainPresenter");
    }

    public void onStart(){
        super.onStart();
        LogUtils.d("onStart MainPresenter");
    }

    public void onResume(){
        super.onResume();
        LogUtils.d("onResume MainPresenter");
    }

    public void onPause(){
        super.onPause();
        LogUtils.d("onPause MainPresenter");
    }

    public void onStop(){
        super.onStop();
        LogUtils.d("onStop MainPresenter");
    }

    public void onDestroy(){
        super.onDestroy();
        LogUtils.d("onDestroy MainPresenter");
    }


    @Override
    public void initRequest() {
        getView().initSuccess(initdata());
    }

    @Override
    public void onLoadMore(int page) {
        new Handler().postDelayed(() -> getView().onLoadMoreSuccess(initdata()),1000);
    }

    @Override
    public void onRefresh() {
        postion = 0;
        new Handler().postDelayed(() -> getView().ononRefreshSuccess(initdata()),1000);
    }

    private ArrayList<String> initdata(){
        ArrayList<String> arrList = new ArrayList();
        for (int i = 0; i < 10; i++) {
//            Person p = new Person();
//            p.setName("gogo--:" + postion);
            arrList.add("gogo--:" + postion);
            postion++;
        }
        return arrList;
    }

}
