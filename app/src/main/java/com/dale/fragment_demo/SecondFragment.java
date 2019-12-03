package com.dale.fragment_demo;

import com.dale.framework.ui.ABBaseFragment;
import com.dale.libdemo.R;
import com.dale.utils.ToastUtils;

public class SecondFragment extends ABBaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initPresenters() {

    }

    @Override
    protected void initViewsAndEvents() {
        if(bundle != null && bundle.containsKey("TestKey")){
            ToastUtils.showLong(bundle.getString("TestKey"));
        }
    }
}
