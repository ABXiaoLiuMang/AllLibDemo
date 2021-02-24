package com.dale.popup_demo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dale.libdemo.BuildConfig;
import com.dale.libdemo.R;
import com.dale.popup_demo.fragment.AllAnimatorDemo;
import com.dale.popup_demo.fragment.CustomAnimatorDemo;
import com.dale.popup_demo.fragment.CustomPopupDemo;
import com.dale.popup_demo.fragment.ImageViewerDemo;
import com.dale.popup_demo.fragment.PartShadowDemo;
import com.dale.popup_demo.fragment.QuickStartDemo;
import com.google.android.material.tabs.TabLayout;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;
import com.lxj.xpopup.util.XPopupUtils;

public class MainActivity extends AppCompatActivity {

    PageInfo[] pageInfos = new PageInfo[]{
            new PageInfo("快速开始", new QuickStartDemo()),
            new PageInfo("局部阴影", new PartShadowDemo()),
            new PageInfo("图片浏览", new ImageViewerDemo()),
            new PageInfo("尝试不同动画", new AllAnimatorDemo()),
            new PageInfo("自定义弹窗", new CustomPopupDemo()),
            new PageInfo("自定义动画", new CustomAnimatorDemo())
    };

    TabLayout tabLayout;
    public ViewPager viewPager;

    LoadingPopupView loadingPopupView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        BarUtils.setStatusBarLightMode(this, true);
//        BarUtils.setNavBarColor(this, Color.RED);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(actionBar.getTitle() + "-" + BuildConfig.VERSION_NAME);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        XPopup.setPrimaryColor(getResources().getColor(R.color.colorPrimary));
//        XPopup.setAnimationDuration(1000);
//        XPopup.setPrimaryColor(Color.RED);
//        ScreenUtils.setLandscape(this);
        loadingPopupView = new XPopup.Builder(this)
                .isDestroyOnDismiss(true)
                .asLoading("嘻嘻嘻嘻嘻");
        loadingPopupView.show();
        loadingPopupView.delayDismiss(1200);

//        BarUtils.setStatusBarVisibility(this, false);
//        BarUtils.setNavBarVisibility(this, false);

//        ToastUtils.showLong(FuckRomUtils.getRomInfo().getName() + FuckRomUtils.getRomInfo().getVersion());
//        ToastUtils.showLong(android.os.Build.MODEL);
//        String str = RomUtils.getRomInfo().toString() + " " + "nav可见：" + XPopupUtils.isNavBarVisible(getWindow()) + "  navHeight: "+ XPopupUtils.getNavBarHeight();
        int windowHeight = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight();
        String str =  " " + "deviceHeight：" + XPopupUtils.getScreenHeight(this)
                + "  getAppHeight: "+ XPopupUtils.getAppHeight(this)
                + "  statusHeight: "+ XPopupUtils.getStatusBarHeight()
                + "  navHeight: "+ XPopupUtils.getNavBarHeight()
                + "  hasNav: "+ XPopupUtils.isNavBarVisible(getWindow());
//        ToastUtils.showLong(str);
        Log.e("tag", str);
    }

    class MainAdapter extends FragmentPagerAdapter {

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return pageInfos[i].fragment;
        }

        @Override
        public int getCount() {
            return pageInfos.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return pageInfos[position].title;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPager.removeAllViews();
        viewPager = null;
        pageInfos = null;
    }

}