package com.dale.fragment_demo;

import android.view.View;

import com.dale.framework.tab.ABTabFragment;
import com.dale.framework.view.TitleBar;
import com.dale.libdemo.R;

public class Tab1Fragment extends ABTabFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab1;
    }

    @Override
    protected void initPresenters() {

    }

    @Override
    protected void initViewsAndEvents() {
       TitleBar titleBar = rootView.findViewById(R.id.titleBar);
       titleBar.setShowLeft(View.INVISIBLE);
        rootView.findViewById(R.id.text).setOnClickListener(v -> {
            start(SecondFragment.class);
//            ((SupportFragment)getParentFragment()).start(SecondFragment.class);
        });
    }


}
