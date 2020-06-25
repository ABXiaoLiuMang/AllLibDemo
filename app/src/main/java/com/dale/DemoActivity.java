package com.dale;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.agentweb_demo.AgentMainActivity;
import com.dale.automore.AutoLoadActivity;
import com.dale.chat.ui.ChatActivity;
import com.dale.dialog.XDialogManager;
import com.dale.emotion.AudioActivity;
import com.dale.emotion.SimpleSessionActivity;
import com.dale.emotion.WxSessionActivity;
import com.dale.fragment_demo.MainFragmentActivity;
import com.dale.framework.ui.ABRefreshActivity;
import com.dale.framework.ui.BasePresenter;
import com.dale.framework.ui.Mode;
import com.dale.framework_demo.MainActivity;
import com.dale.image_demo.lzy.ImagePickerActivity;
import com.dale.libdemo.R;
import com.dale.location_demo.LocationActivity;
import com.dale.net_demo.NetActivity;
import com.dale.popup_demo.PopupMainActivity;
import com.dale.push_demo.PushActivity;
import com.dale.stateview_demo.StateTestActivity;
import com.dale.utils.LogUtils;
import com.dale.utils.MMKVUtil;
import com.dale.utils.ToastUtils;
import com.dale.utils.UiMessageUtils;
import com.dale.utils.WeakHandler;
import com.dale.view.RecyclerViewDivider;
import com.dale.view.XMarqueView;
import com.dale.viewmodel.MyTestModelActivity;
import com.dale.xweb.H5Activity;
import com.dale.zxing_demo.ZxingActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;



//import com.dale.kotlinlib.simple.ObjectUtilKt;
//import static com.dale.kotlinlib.simple.ObjectUtilKt.lastChar;
//import static com.dale.kotlinlib.simple.ObjectUtilKt.toast;

public class DemoActivity extends ABRefreshActivity<String, BasePresenter> {
//    @Override
//    protected void initPresenters() {
////        https://download.csdn.net/download/xiaoyu5256/9809854 设计模式下载 （设计模式之禅）
//    }


    XDialogManager xDialogManager = new XDialogManager();

    @SuppressLint("CheckResult")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        PermissionGen.with(this)
//                .addRequestCode(100)
//                .permissions(Manifest.permission.RECORD_AUDIO
//                        , Manifest.permission.WRITE_EXTERNAL_STORAGE
//                        , Manifest.permission.WAKE_LOCK
//                        , Manifest.permission.READ_EXTERNAL_STORAGE)
//                .request();
//        GlideApp.with(this).load("").placeholder(R.mipmap.arrow).error(R.mipmap.arrow).addListener(new RequestListener<Drawable>() {
//            @Override
//            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                return false;
//            }
//        });


        //测试扩展函数
//        toast(this,"");
//        lastChar("");
//        ObjectUtilKt.toast(this,"");
//        ObjectUtilKt.lastChar("");

    }

    @Override
    protected void initViewsAndEvents() {
        MMKVUtil.put("keyTest","可以的");
        super.initViewsAndEvents();

        initTestData();

        UiMessageUtils.getInstance().addListener(new UiMessageUtils.UiMessageCallback(){

            @Override
            public void handleMessage(@NonNull UiMessageUtils.UiMessage localMessage) {
                ToastUtils.showLong("id:" + localMessage.getId() + " obj:" + localMessage.getObject().toString());
                LogUtils.d("id:" + localMessage.getId() + " obj:" + localMessage.getObject().toString());
            }
        });

        //发送消息
//        UiMessageUtils.getInstance().send(0,"hahaah");

//        stateLayout.setState(StateLayout.STATE_NET_ERROR);
    }

    private void initTestData(){
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
        list.add("emotion 实例");
        list.add("录音 实例");
        list.add("17 viewModel");
        list.add("TestShare");
        list.add("H5Activity");
        listAdapter.setNewData(list);


    }

    @Override
    public int getMode() {
        return Mode.BOTH;
    }

    @Override
    public BaseQuickAdapter<String, BaseViewHolder> getListAdapter() {
        return new DemoAdapter(R.layout.item_layout);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(mContext,3);
    }

    boolean isWeiXin = false;
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
                    goActivity(ChatActivity.class);
//                    goActivity(WorkActivity.class);
                    break;
                case 13:
                    switchNightMode();
                    break;
                case 14:
                    goActivity(AutoLoadActivity.class);
                    break;
                case 15:
                    if(isWeiXin){
                        isWeiXin = false;
                        goActivity(WxSessionActivity.class);
                    }else {
                        isWeiXin = true;
                        goActivity(SimpleSessionActivity.class);
                    }
                    break;
                case 16:
                    goActivity(AudioActivity.class);
                    break;
                case 17:
                    goActivity(MyTestModelActivity.class);
                    break;
                case 18:
//                    goActivity(TestShareViewActivity.class);
                    break;
                case 19:
//                    goActivity(H5Activity.class);
                    gotest();
                    break;
            }
    }

    private void gotest(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("content://" + "tblccn"));
        startActivity(intent);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        new WeakHandler().postDelayed(() -> {
            initTestData();
            refreshLayout.finishLoadMore();
            refreshDelegate.setRefreshMode(refreshLayout,getMode());
        },1000);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        new WeakHandler().postDelayed(() -> {
            initTestData();
            refreshLayout.finishRefresh();
        },1000);
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


    @Override
    protected void onResume() {
        super.onResume();
        ToastUtils.showLong(MMKVUtil.getString("keyTest","不可以的"));

        test();
    }

    private void test(){
//        User user = new User();
//        user.setUserId("1000");
//        user.setName("辣妹子");
//
//        UserDao dao = AppDatabase.get().userDao();
//        dao.insert(user);
//
//        List<User> users = dao.getItems();
//        for (User u : users){
//            LogUtils.d("---------room--------------:" + u.toString());
//        }
//
//        Phone phone = new Phone();
//        phone.setName("罗纳尔多");
//        phone.setPhone("13554254582");
//
//        PhoneDao phoneDao = AppDatabase.get().phoneDao();
//        phoneDao.insert(phone);
//
//        List<Phone> phones = phoneDao.getItems();
//        for (Phone p : phones){
//            LogUtils.d("---------room--------------:" + p.toString());
//        }
    }
}
