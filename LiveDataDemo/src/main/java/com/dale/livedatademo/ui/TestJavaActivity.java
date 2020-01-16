package com.dale.livedatademo.ui;

import androidx.lifecycle.ViewModelProvider;

import com.dale.framework.ui.ABBaseActivity;
import com.dale.livedatademo.R;
import com.dale.livedatademo.model.TestViewModel;

public class TestJavaActivity extends ABBaseActivity {

    TestViewModel testViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void createProvider() {
    }

    @Override
    protected void initViewsAndEvents() {

    }
}
