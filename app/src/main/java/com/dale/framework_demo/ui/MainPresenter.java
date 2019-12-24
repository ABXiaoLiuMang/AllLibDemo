package com.dale.framework_demo.ui;

import com.dale.framework.ui.BasePresenter;
import com.dale.utils.LogUtils;
import com.dale.utils.WeakHandler;

import java.util.ArrayList;


public class MainPresenter extends BasePresenter<MainContract.IView> implements MainContract.IPresenter {

    int postion = 0;

    public OtherPresenter otherPresenter = new OtherPresenter();

    public void onCreate(){
        super.onCreate();
//        initRequest();
        LogUtils.d("onCreate MainPresenter");
        attachChildPresenter(otherPresenter);
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


        new WeakHandler().postDelayed(() -> {
            if(getView() != null){
                getView().onLoadMoreSuccess(initdata());
            }
        },10000);
    }

    @Override
    public void onRefresh() {
        postion = 0;
        new WeakHandler().postDelayed(() -> {
            if(getView() != null){
                getView().ononRefreshSuccess(initdata());
            }
        },10000);
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
