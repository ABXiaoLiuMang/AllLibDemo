package com.dale.framework.tab;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.dale.framework.R;
import com.dale.framework.ui.ABBaseFragment;
import magicindicator.FragmentContainerHelper;
import magicindicator.MagicIndicator;
import magicindicator.buildins.commonnavigator.CommonNavigator;
import magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;

/**
 * create by Dale
 * create on 2019/5/30
 * description: Tab 基类
 */
public abstract class ABMainTabFragment extends ABBaseFragment implements TabAdapter.OnTabClickListener {
    TabAdapter commonAdapter;
    FragmentContainerHelper tabHelper = new FragmentContainerHelper();
    MagicIndicator mainTabView;
    MainTab[] mainTabs;
    ABTabFragment[] fragments;
    ArrayMap<Integer, ABTabFragment> supportFragments;
    int currentIndex = 0;

    protected abstract MainTab[] getMainTabs();

    protected abstract ABTabFragment[] getFragments();

    protected @ColorRes int getTextColor(){
        return R.color.text_color;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tab_main;
    }

    @Override
    protected void initPresenters() {
    }

    @Override
    protected void initViewsAndEvents() {
        mainTabView = rootView.findViewById(R.id.mainTabView);
        mainTabs = getMainTabs();
        fragments = getFragments();
        supportFragments = new ArrayMap<>(mainTabs.length);
        loadMultipleRootFragment(R.id.fragment_content, currentIndex, fragments);
        initMagicIndicators();
    }

    private void initMagicIndicators() {
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        commonNavigator.setAdjustMode(true);
        commonAdapter = new TabAdapter(mContext, mainTabs);
        commonAdapter.setTextColor(getTextColor());
        commonAdapter.setTabClickListener(this);
        commonNavigator.setAdapter(commonAdapter);
        mainTabView.setNavigator(commonNavigator);
        tabHelper.attachMagicIndicator(mainTabView);
        tabHelper.handlePageSelected(currentIndex, false);
    }

    @Override
    public void onClick(CommonNavigatorAdapter adapter, View view, int postion) {
        if (postion == currentIndex) {
            return;
        }
        ABTabFragment tabFragment =  fragments[postion];
        ABTabFragment preTabFragment = fragments[currentIndex];
        showHideFragment(tabFragment, preTabFragment);
        currentIndex = postion;
        tabHelper.handlePageSelected(currentIndex, false);
    }

}
