package com.dale.stateview_demo;

import android.view.View;

import com.dale.framework.ui.ABBaseActivity;
import com.dale.framework.view.StateLayout;
import com.dale.libdemo.R;
import com.dale.stateview_demo.state.Gloading;
import com.dale.utils.RandomUtils;
import com.dale.utils.ToastUtils;
import com.dale.utils.WeakHandler;
import com.lxj.statelayout.State;

public class StateTestActivity extends ABBaseActivity implements StateLayout.OnRetryListener {

   StateLayout stateLayout;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_state_test;
    }

    @Override
    protected void initViewsAndEvents() {
        stateLayout = findViewById(R.id.stateLayout);
        stateLayout.setVisibility(View.VISIBLE);
        findViewById(R.id.tv_content).setVisibility(View.GONE);

        int postion = RandomUtils.getRandom(100);
        switch (postion % 3){
            case 0:
                stateLayout.setState(StateLayout.STATE_LOADING);
                break;
            case 1:
                stateLayout.setState(StateLayout.STATE_NET_ERROR);
                break;
            case 2:
                stateLayout.setState(StateLayout.STATE_EMPTY);
                break;
        }
        stateLayout.setOnRetryListener(this);
    }

    @Override
    public void onRetry() {
       ToastUtils.showLong("重试一次");
    }
}
