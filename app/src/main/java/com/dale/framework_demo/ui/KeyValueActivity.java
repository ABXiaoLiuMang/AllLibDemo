package com.dale.framework_demo.ui;

import com.dale.framework.ui.ABBaseActivity;
import com.dale.libdemo.R;
import com.dale.utils.StatusBarUtil;


public class KeyValueActivity extends ABBaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_key_value;
    }


    @Override
    protected void initViewsAndEvents() {
       showProgressDialog();
    }

    @Override
    protected void initSystemBar() {
        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.setDarkMode(this);//状态栏图标黑色
    }


}
