package com.dale.livedatademo.navigation;

import androidx.navigation.Navigation;

import com.dale.framework.ui.ABBaseActivity;
import com.dale.livedatademo.R;

public class NavigationMainActivity extends ABBaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_navigation_main;
    }

    @Override
    protected void createProvider() {

    }

    @Override
    protected void initViewsAndEvents() {
        Navigation.findNavController(this,R.id.nav_host_fragment).navigateUp();
    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_navigation_main);
//        //代码配置
//        Navigation.findNavController(this,R.id.nav_host_fragment).navigateUp();
//    }
}
