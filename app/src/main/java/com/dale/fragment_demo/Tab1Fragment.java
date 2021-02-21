package com.dale.fragment_demo;

import android.os.Bundle;
import android.view.View;

import com.dale.framework.view.TitleBar;
import com.dale.framework_demo.LiveDataManager;
import com.dale.framework_demo.ui.OtherContract;
import com.dale.framework_demo.ui.OtherPresenter;
import com.dale.libdemo.R;
import com.dale.net.callback.NetObserver;
import com.dale.net.exception.ErrorMessage;
import com.dale.utils.LogUtils;
import com.dale.utils.ToastUtils;

public class Tab1Fragment extends ABTabFragment<OtherPresenter> implements OtherContract.IView {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab1;
    }

    @Override
    protected void initViewsAndEvents() {
       TitleBar titleBar = rootView.findViewById(R.id.titleBar);
       titleBar.setShowLeft(View.INVISIBLE);
        rootView.findViewById(R.id.text).setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("TestKey","艹可以啊");
            start(SecondFragment.class,bundle);

//            ((SupportFragment)getParentFragment()).start(SecondFragment.class);
        });

//        presenter.testOther();
        presenter.getHome();

        LiveDataManager.getInstance().testPrice.observe(this,new NetObserver<String>(){

            @Override
            protected void onSuccess(String s) {
                LogUtils.d("成功啦");
            }

            @Override
            protected void onError(ErrorMessage errorMessage) {
                LogUtils.d("失败啦");
            }
        });
    }


    @Override
    public void test() {
        ToastUtils.showLong("测试");
    }
}
