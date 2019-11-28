package com.dale.framework_demo.ui;

import androidx.fragment.app.Fragment;

import com.dale.framework_demo.fragment.Tab1Fragment;
import com.dale.framework.ui.ABTabActivity;
import com.dale.libdemo.R;


public class TabMYBaseActivity extends ABTabActivity {

    @Override
    protected String[] getTabTexts() {
        return new String[]{"设备","附件","发现"};
    }

    @Override
    protected int[] getTabImageResIds() {
        return new int[]{R.drawable.tab_select_btn_sc,R.drawable.tab_select_btn_fl,R.drawable.tab_select_btn_fj};
    }

    @Override
    protected Class<? extends Fragment>[] getFragments() {
        return new Class[]{Tab1Fragment.class,Tab1Fragment.class,Tab1Fragment.class};
    }

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        setTextMsg(2,"99");
    }
}
