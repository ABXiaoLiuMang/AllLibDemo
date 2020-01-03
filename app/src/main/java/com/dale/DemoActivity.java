package com.dale;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.agentweb_demo.AgentMainActivity;
import com.dale.fragment_demo.MainFragmentActivity;
import com.dale.framework.ui.BasePresenter;
import com.dale.framework_demo.MainActivity;
import com.dale.image_demo.lzy.ImagePickerActivity;
import com.dale.kotlinlib.array.TestArray;
import com.dale.libdemo.R;
import com.dale.location_demo.LocationActivity;
import com.dale.net_demo.NetActivity;
import com.dale.popup_demo.PopupMainActivity;
import com.dale.push_demo.PushActivity;
import com.dale.resolver.TypeResolverActivity;
import com.dale.stateview_demo.StateTestActivity;
import com.dale.thread_demo.ThreadActivity;
import com.dale.view.RecyclerViewDivider;
import com.dale.view.XMarqueView;
import com.dale.framework.ui.ABRefreshActivity;
import com.dale.framework.ui.Mode;
import com.dale.worker_demo.WorkActivity;
import com.dale.zxing_demo.ZxingActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends ABRefreshActivity<String, BasePresenter> {
//    @Override
//    protected void initPresenters() {
////        https://download.csdn.net/download/xiaoyu5256/9809854 设计模式下载  （设计模式之禅）
//    }

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
        list.add("Fragment 实例");
        list.add("ThreadPool 实例");
        list.add("statedemo 实例");
        list.add("Video 实例");
        list.add("Worker 实例");
        listAdapter.setNewData(list);
    }

    @Override
    public int getMode() {
        return Mode.DISABLED;
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
                case 8:
                    goActivity(MainFragmentActivity.class);
                    break;
                case 9:
                    goActivity(ThreadActivity.class);
                    break;
                case 10:
                    goActivity(StateTestActivity.class);
                    break;
                case 11:
                    goActivity(com.dale.video_demo.MainActivity.class);
                    break;
                case 12:
                    goActivity(WorkActivity.class);
                    break;
            }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public View getHeaderView() {
        View view = View.inflate(mContext,R.layout.item_head,null);
        XMarqueView xMarqueView = view.findViewById(R.id.xMarqueView);
        List<String> list = new ArrayList<>();
        list.add("会将它的参数和每个条件比较，直到找到一个合适的分支，否则会走默认分支");
        list.add("会将它的参数和每个条件比较，直到找到一个合适的分支，否则会走默认分支");
        list.add("会将它的参数和每个条件比较，直到找到一个合适的分支，否则会走默认分支");
        xMarqueView.setList(list);
        xMarqueView.autoScroll(this);
        return view;
    }


    @Override
    public RecyclerView.ItemDecoration getItemDecoration() {
        return new RecyclerViewDivider.Builder(this)
                .setOrientation(RecyclerViewDivider.VERTICAL)
                .setStyle(RecyclerViewDivider.Style.BETWEEN)
                .setColorRes(R.color.anhei)
                .setSize(1.5f)
                .setStartSkipCount(3)
                .setMarginLeft(72)
                .setMarginRight(8)
                .build();
    }
}
