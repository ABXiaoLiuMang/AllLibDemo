package com.dale;

import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.agentweb_demo.AgentMainActivity;
import com.dale.framework.ui.ABRefreshActivity;
import com.dale.framework.ui.Mode;
import com.dale.framework_demo.MainActivity;
import com.dale.image_demo.lzy.ImagePickerActivity;
import com.dale.libdemo.R;
import com.dale.location_demo.LocationActivity;
import com.dale.net_demo.NetActivity;
import com.dale.popup_demo.PopupMainActivity;
import com.dale.push_demo.PushActivity;
import com.dale.zxing_demo.ZxingActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoActivity extends ABRefreshActivity<String> {
    @Override
    protected void initPresenters() {

    }

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        List<String> list = new ArrayList<>();
        list.add("frameword 实例");
        list.add("location 实例");
        list.add("net 实例");
        list.add("push 实例");
        list.add("zxing 实例");
        list.add("ImagePicker 实例");
        list.add("PopupDialog 实例");
        list.add("AgentWeb 实例");
        listAdapter.setNewData(list);
    }

    @Override
    public Mode getMode() {
        return super.getMode();
    }

    @Override
    public BaseQuickAdapter<String, BaseViewHolder> getListAdapter() {
        return new DemoAdapter(R.layout.item_layout);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            switch (position){
                case 0:
                    goActivity(MainActivity.class);
                    break;
                case 1:
                    goActivity(LocationActivity.class);
                    break;
                case 2:
                    goActivity(NetActivity.class);
                    break;
                case 3:
                    goActivity(PushActivity.class);
                    break;
                case 4:
                    goActivity(ZxingActivity.class);
                    break;
                case 5:
                    goActivity(ImagePickerActivity.class);
                    break;
                case 6:
                    goActivity(PopupMainActivity.class);
                    break;
                case 7:
                    goActivity(AgentMainActivity.class);
                    break;
            }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }
}
