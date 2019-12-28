package com.dale;

import com.dale.framework.ui.ABBaseActivity;
import com.dale.libdemo.R;
import com.dale.view.SplashTextView;

public class SplashActivity extends ABBaseActivity implements SplashTextView.onFinishListener {

    SplashTextView splashTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViewsAndEvents() {
        splashTextView = findViewById(R.id.splashTextView);
        splashTextView.setOnFinishListener(this);
    }

    @Override
    public void onFinish() {
       goActivity(GuideActivity.class);
       finish();
    }


}
