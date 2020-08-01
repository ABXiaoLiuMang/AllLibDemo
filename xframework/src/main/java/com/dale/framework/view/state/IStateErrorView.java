package com.dale.framework.view.state;

public interface IStateErrorView extends IStateView{

    void setRetryClickListener(OnRetryListener retryListener);

    interface OnRetryListener{
        void onRetry();
    }
}
