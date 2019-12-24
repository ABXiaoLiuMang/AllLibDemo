package com.dale.framework_demo.ui;

import com.dale.framework.ui.BasePresenter;
import com.dale.framework_demo.LiveDataManager;
import com.dale.framework_demo.api.ApiService;
import com.dale.net.NetSdk;
import com.dale.utils.LogUtils;


public class OtherPresenter extends BasePresenter<OtherContract.IView> implements OtherContract.IPresenter {

    public void onCreate(){
        super.onCreate();
        LogUtils.d("Dream","onCreate OtherPresenter");
        if(getView() !=null){
            getView().test();
        }
    }

    public void onStart(){
        super.onStart();
        LogUtils.d("Dream","onStart OtherPresenter");
    }

    public void onResume(){
        super.onResume();
        LogUtils.d("Dream","onResume OtherPresenter");
    }

    public void onPause(){
        super.onPause();
        LogUtils.d("Dream","onPause OtherPresenter");
    }

    public void onStop(){
        super.onStop();
        LogUtils.d("Dream","onStop OtherPresenter");
    }

    public void onDestroy(){
        super.onDestroy();
        LogUtils.d("Dream","onDestroy OtherPresenter");
    }

    @Override
    public void testOther() {
//        NetSdk.create(ApiService.class)
//                .getSeries()
//                .baseUrl("http://api2.ibaozou.com/")
//                .params("page",String.valueOf(1))
//                .params("per_page",String.valueOf(24))
//                .asLife(getLifecycleOwner())
//                .send(LiveDataManager.getInstance().testPrice);
        if(getView() != null){
            getView().test();
        }
    }

    @Override
    public void getHome() {
        NetSdk.create(ApiService.class)
                .getHome()
                .baseUrl("http://api2.ibaozou.com/")
                .asLife(getLifecycleOwner())
                .send(LiveDataManager.getInstance().testPrice);
    }
}
