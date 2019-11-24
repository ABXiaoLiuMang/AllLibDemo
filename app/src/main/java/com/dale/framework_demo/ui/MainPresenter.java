package com.dale.framework_demo.ui;

import android.os.Handler;

import androidx.lifecycle.LifecycleOwner;

import com.dale.framework_demo.Person;
import com.dale.framework.ui.BasePresenter;
import com.dale.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;


public class MainPresenter extends BasePresenter<MainContract.IView> implements MainContract.IPresenter {

    public MainPresenter(LifecycleOwner lifecycleOwner, MainContract.IView iView) {
        super(lifecycleOwner, iView);
    }

    public void onCreate(){
        super.onCreate();
//        initRequest();
        LogUtils.d("Dream","onCreate MainPresenter");
    }

    public void onStart(){
        super.onStart();
        LogUtils.d("Dream","onStart MainPresenter");
    }

    public void onResume(){
        super.onResume();
        LogUtils.d("Dream","onResume MainPresenter");
    }

    public void onPause(){
        super.onPause();
        LogUtils.d("Dream","onPause MainPresenter");
    }

    public void onStop(){
        super.onStop();
        LogUtils.d("Dream","onStop MainPresenter");
    }

    public void onDestroy(){
        super.onDestroy();
        LogUtils.d("Dream","onDestroy MainPresenter");
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
        new Handler().postDelayed(() -> getView().ononRefreshSuccess(initdata()),1000);
    }

    private List<Person> initdata(){
        List arrList = new ArrayList();
        arrList.add(new Person());
        arrList.add(new Person());
        arrList.add(new Person());
        arrList.add(new Person());
        arrList.add(new Person());
        arrList.add(new Person());
        arrList.add(new Person());
        arrList.add(new Person());
        arrList.add(new Person());
        arrList.add(new Person());
        arrList.add(new Person());
        arrList.add(new Person());
        arrList.add(new Person());
        arrList.add(new Person());
        arrList.add(new Person());
        arrList.add(new Person());
        arrList.add(new Person());
        arrList.add(new Person());
        arrList.add(new Person());
        arrList.add(new Person());
        return arrList;
    }

}
