package com.dale.framework_demo.ui;

import android.widget.TextView;

import androidx.core.widget.NestedScrollView;

import com.dale.framework_demo.LiveDataManager;
import com.dale.framework.ui.ABBaseActivity;
import com.dale.framework.util.ScrollChangeHelper;
import com.dale.framework.view.TitleBar;
import com.dale.libdemo.R;
import com.dale.net.callback.NetObserver;
import com.dale.net.exception.ErrorMessage;
import com.dale.utils.LogUtils;
import com.dale.utils.SizeUtils;


public class ScrollActivity extends ABBaseActivity {


    OtherPresenter otherPresenter;

    private NestedScrollView scrollView;
    TitleBar titleBar;
    TextView tv1;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_scroll;
    }

//    @Override
//    protected void initPresenters() {
//        otherPresenter = new OtherPresenter();
//    }


    @Override
    protected void initViewsAndEvents() {
        tv1 = findViewById(R.id.tv1);
        scrollView = findViewById(R.id.scrollView);
        titleBar = findViewById(R.id.titleBar);
        new ScrollChangeHelper.Builder().scrollHeight(SizeUtils.dp2px(200)).setAlphaView(titleBar).setNestedScrollView(scrollView).build();
//        otherPresenter.getHome();
        LiveDataManager.getInstance().testPrice.observe(this,new NetObserver<String>(){

            @Override
            protected void onSuccess(String s) {
                tv1.setText("s:" + s);
                LogUtils.d("ScrollActivity:" + s);
            }

            @Override
            protected void onError(ErrorMessage errorMessage) {
                tv1.setText("s:" + errorMessage.getMessage());
            }
        });

    }

//    protected void initSystemBar(){
//        StatusBarUtil.setTransparentForWindow(this);
//        StatusBarUtil.setDarkMode(this);
//    }

}
