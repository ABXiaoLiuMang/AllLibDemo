package com.dale.fragment_demo;

import com.dale.framework.ui.ABBaseFragment;
import com.dale.libdemo.R;

public class SecondFragment extends ABBaseFragment {

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initPresenters() {

    }

    @Override
    protected void initViewsAndEvents() {

    }
}
