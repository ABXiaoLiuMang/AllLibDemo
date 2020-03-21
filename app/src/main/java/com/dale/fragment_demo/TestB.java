package com.dale.fragment_demo;

import android.widget.Button;


import com.dale.framework.ui.ABBaseFragment;
import com.dale.libdemo.R;

import butterknife.BindView;
import butterknife.OnClick;

public class TestB extends ABBaseFragment {
    @BindView(R.id.btn_0)
    Button btn0;

    @Override
    protected int getLayoutId() {
        return R.layout.a_a;
    }



    @Override
    protected void initViewsAndEvents() {


    }

    @OnClick(R.id.btn_0)
    public void onViewClicked() {

    }
}
