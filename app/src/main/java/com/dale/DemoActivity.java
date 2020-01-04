package com.dale;

import android.os.SystemClock;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.agentweb_demo.AgentMainActivity;
import com.dale.automore.AutoLoadActivity;
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
import com.dale.utils.MMKVUtil;
import com.dale.utils.ToastUtils;
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
        list.add("test 实例");
        list.add("14auto 实例");
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
//                    goActivity(ThreadActivity.class);
                    setTing();
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
                case 13:
                    switchNightMode();
                    break;
                case 14:
                    goActivity(AutoLoadActivity.class);
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


    /**
     * 日夜间模式切换
     */
    private void switchNightMode() {
        boolean isNight = MMKVUtil.getBoolean("SWITCH_MODE_KEY", false);
        if (isNight) {
            // 日间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            MMKVUtil.put("SWITCH_MODE_KEY", false);
        } else {
            // 夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            MMKVUtil.put("SWITCH_MODE_KEY", true);
        }
        recreate();
    }

    boolean canEnviron = false;
    private final static int COUNTS = 5;//点击次数
    private final static long DURATION = 3 * 1000;//规定有效时间
    private long[] mHits = new long[COUNTS];
    private void setTing(){
        if (!canEnviron) {
            /**
             * 实现双击方法
             * src 拷贝的源数组
             * srcPos 从源数组的那个位置开始拷贝.
             * dst 目标数组
             * dstPos 从目标数组的那个位子开始写数据
             * length 拷贝的元素的个数
             */
            System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
            //实现左移，然后最后一个位置更新距离开机的时间，如果最后一个时间和最开始时间小于DURATION，即连续5次点击
            mHits[mHits.length - 1] = SystemClock.uptimeMillis();
            if (mHits[0] >= (SystemClock.uptimeMillis() - DURATION)) {
                ToastUtils.showLong("再次点击进行进入环境切换界面");
                canEnviron = true;
            }
        } else {
            ToastUtils.showLong("跳转成功");
        }
    }
}
