package com.dale.view;

import android.os.Bundle;
import android.widget.Button;

import com.dale.framework.ui.ABBaseActivity;
import com.dale.libdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestShareViewActivity extends ABBaseActivity {
    ShareView mShareView2;
    @BindView(R.id.test)
    Button test;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shareview;
    }

    @Override
    protected void initViewsAndEvents() {
//        mShareView2 = findViewById(R.id.shareView);
//        mShareView2.setUrl("http://01.minipic.eastday.com/20161229/20161229105231_a71df75a6b5be4281151b54b2bbd7362_4.jpeg");
//        mShareView2.setUrl("http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg");
    }


    @OnClick(R.id.test)
    public void onViewClicked() {
        mShareView2.refreshImageLayout();
    }
}
