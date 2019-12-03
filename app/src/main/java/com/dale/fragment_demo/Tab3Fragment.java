package com.dale.fragment_demo;

import android.view.View;

import com.dale.framework.tab.ABTabFragment;
import com.dale.framework.view.TitleBar;
import com.dale.libdemo.R;

public class Tab3Fragment extends ABTabFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab3;
    }

    @Override
    protected void initPresenters() {

    }

    @Override
    protected void initViewsAndEvents() {
        TitleBar titleBar = rootView.findViewById(R.id.titleBar);
        titleBar.setShowLeft(View.INVISIBLE);

        rootView.findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(SecondFragment.class);
//                ((SupportFragment)getParentFragment()).start(SecondFragment.class);
            }
        });
    }
}
