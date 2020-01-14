package com.dale.viewmodel;

import android.widget.TextView;

import com.dale.libdemo.R;

public class MyTestModelActivity extends ABModelViewActivity<DomeModel> {

    TextView tv_test;
    int count = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_test_model;
    }

    @Override
    protected void initViewsAndEvents() {

//        DomeModel viewModel = new ViewModelProvider(this).get(DomeModel.class);

        tv_test = findViewById(R.id.tv_test);
        findViewById(R.id.btn_get).setOnClickListener(v -> {
            count++;
            mViewModel.setDomeInfo(count);
        });

        mViewModel.getmDomeLiveData().observe(this, s -> tv_test.setText(s));
    }
}
