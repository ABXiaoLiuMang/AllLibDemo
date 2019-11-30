package com.dale.fragment_demo;

import com.dale.framework.ui.ABBaseActivity;
import com.dale.libdemo.R;

public class MainFragmentActivity extends ABBaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_fragment;
    }

    @Override
    protected void initPresenters() {

    }

    @Override
    protected void initViewsAndEvents() {
        loadRootFragment(R.id.fl_container, MainFragment.newInstance());
    }
}
