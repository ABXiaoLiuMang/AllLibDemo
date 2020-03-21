package com.dale.stateview_demo;

import android.view.View;
import android.widget.LinearLayout;

import com.dale.framework.ui.ABBaseActivity;
import com.dale.framework.view.StateLayout;
import com.dale.libdemo.R;
import com.dale.utils.RandomUtils;
import com.dale.utils.ToastUtils;

public class StateTestActivity extends ABBaseActivity implements StateLayout.OnRetryListener {

    StateLayout stateLayout;
    LinearLayout tv_content;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_state_test;
    }

    @Override
    protected void initViewsAndEvents() {
        stateLayout = findViewById(R.id.stateLayout);
        tv_content = findViewById(R.id.tv_content);
        stateLayout.setVisibility(View.VISIBLE);
        findViewById(R.id.tv_content).setVisibility(View.GONE);

        int postion = RandomUtils.getRandom(100);
        switch (postion % 4){
            case 0:
                stateLayout.setState(StateLayout.STATE_LOADING);
                break;
            case 1:
                stateLayout.setState(StateLayout.STATE_NET_ERROR);
                break;
            case 2:
                stateLayout.setState(StateLayout.STATE_EMPTY);
                break;
            case 3:
                stateLayout.setVisibility(View.GONE);
                tv_content.setVisibility(View.VISIBLE);
                break;
        }
        stateLayout.setOnRetryListener(this);
    }

    @Override
    public void onRetry() {
       ToastUtils.showLong("重试一次");
        stateLayout.setVisibility(View.GONE);
        tv_content.setVisibility(View.VISIBLE);
    }
}
