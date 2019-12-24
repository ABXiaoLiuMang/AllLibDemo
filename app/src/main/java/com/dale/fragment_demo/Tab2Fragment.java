package com.dale.fragment_demo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.dale.framework.tab.ABTabFragment;
import com.dale.framework.view.TitleBar;
import com.dale.libdemo.R;
import com.dale.utils.LogUtils;

public class Tab2Fragment extends ABTabFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab2;
    }

    @Override
    protected void initViewsAndEvents() {
        TitleBar titleBar = rootView.findViewById(R.id.titleBar);
        titleBar.setShowLeft(View.INVISIBLE);

        rootView.findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("TestKey","cao ni mei de");
                start(SecondFragment.class,bundle);
//                ((SupportFragment)getParentFragment()).start(SecondFragment.class);
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        LogUtils.d("Tab2Fragment : onLazyInitView");
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        LogUtils.d("Tab2Fragment : onSupportVisible");
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        LogUtils.d("Tab2Fragment : onSupportInvisible");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d("Tab2Fragment : onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.d("Tab2Fragment : onStart");
    }
}
