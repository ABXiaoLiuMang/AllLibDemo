package com.dale.fragment_demo;

import com.dale.framework.tab.ABMainTabFragment;
import com.dale.framework.tab.ABTabFragment;
import com.dale.framework.tab.MainTab;
import com.dale.libdemo.R;

public class MainTabFragment extends ABMainTabFragment {

    public static MainTabFragment newInstance() {
        return new MainTabFragment();
    }

    @Override
    protected MainTab[] getMainTabs() {
        MainTab[] mainTabs = new MainTab[3];
        mainTabs[0] = new MainTab("首页","", R.mipmap.icon_tab_fj_normal,R.mipmap.icon_tab_fj_hold);
        mainTabs[1] = new MainTab("更多","8",R.mipmap.icon_tab_fj_normal,R.mipmap.icon_tab_fj_hold);
        mainTabs[2] = new MainTab("我的","",R.mipmap.icon_tab_fj_normal,R.mipmap.icon_tab_fj_hold);
        return mainTabs;
    }

    @Override
    protected ABTabFragment[] getFragments() {
        ABTabFragment[] fragments = new ABTabFragment[3];
        fragments[0] = new Tab1Fragment();
        fragments[1] = new Tab2Fragment();
        fragments[2] = new Tab3Fragment();
        return fragments;
    }
}
