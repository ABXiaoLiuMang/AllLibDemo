package com.dale.fragment_demo;

import android.view.View;

import com.dale.framework.ui.ABBaseFragment;
import com.dale.libdemo.R;

public class MainFragment extends ABBaseFragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_f_main;
    }

    @Override
    protected void initPresenters() {

    }

    @Override
    protected void initViewsAndEvents() {
         rootView.findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 start(SecondFragment.newInstance());
             }
         });
    }
}
