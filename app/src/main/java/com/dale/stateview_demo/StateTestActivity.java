package com.dale.stateview_demo;

import com.dale.framework.ui.ABBaseActivity;
import com.dale.libdemo.R;
import com.dale.stateview_demo.state.Gloading;
import com.dale.utils.ToastUtils;
import com.dale.utils.WeakHandler;

public class StateTestActivity extends ABBaseActivity {

    protected Gloading.Holder mHolder;


    @Override
    protected int getLayoutId() {
        return R.layout.x_activity_main;
    }

    @Override
    protected void initViewsAndEvents() {
        showLoading();
        new WeakHandler().postDelayed(() -> {
            ToastUtils.showLong("成功了");
            showLoadSuccess();
        },2000);
    }








    protected void initLoadingStatusViewIfNeed() {
        if (mHolder == null) {
            //bind status view to activity root view by default
            mHolder = Gloading.getDefault().wrap(findViewById(R.id.test_state)).withRetry(new Runnable() {
                @Override
                public void run() {
                    onLoadRetry();
                }
            });
        }
    }

    protected void onLoadRetry() {
        // override this method in subclass to do retry task
    }

    public void showLoading() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoading();
    }

    public void showLoadSuccess() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoadSuccess();
    }

    public void showLoadFailed() {
        initLoadingStatusViewIfNeed();
        mHolder.showLoadFailed();
    }

    public void showEmpty() {
        initLoadingStatusViewIfNeed();
        mHolder.showEmpty();
    }
}
