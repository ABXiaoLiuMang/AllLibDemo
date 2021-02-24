package com.dale;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.viewpager.widget.ViewPager;

import com.dale.framework.ui.ABBaseActivity;
import com.dale.libdemo.R;
import com.dale.view.viewpager.XBanner;
import com.dale.view.viewpager.entity.LocalImageInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class GuideActivity extends ABBaseActivity {

    @BindView(R.id.xbanner)
    XBanner xbanner;
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void createProvider() {

    }

    @Override
    protected void initViewsAndEvents() {
        //test
        goActivity(DemoActivity.class);
        finish();
        //test
        List<LocalImageInfo> localImageInfoList=new ArrayList<>();
        localImageInfoList.add(new LocalImageInfo(R.mipmap.guide_01));
        localImageInfoList.add(new LocalImageInfo(R.mipmap.guide_02));
        localImageInfoList.add(new LocalImageInfo(R.mipmap.guide_03));
        xbanner.setBannerData(localImageInfoList);
        xbanner.loadImage((banner, model, view, position) -> ((ImageView) view).setImageResource(((LocalImageInfo) model).getXBannerUrl()));
        xbanner.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == xbanner.getRealCount() - 1) {
                    btn.setVisibility(View.VISIBLE);
                } else {
                    btn.setVisibility(View.GONE);
                }
            }
        });
    }


    @OnClick(R.id.btn)
    public void onViewClicked() {
        goActivity(DemoActivity.class);
        finish();
    }


}
