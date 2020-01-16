package com.dale.livedatademo.ui;

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
        testViewModel = getDefaultViewModelProviderFactory().create(TestViewModel.class);
    }

    @Override
    protected void initViewsAndEvents() {

    }
}
