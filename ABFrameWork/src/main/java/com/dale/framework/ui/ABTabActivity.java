//package com.dale.framework.ui;
//
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TabHost;
//import android.widget.TextView;
//
//import androidx.fragment.app.Fragment;
//
//import com.dale.framework.R;
//import com.dale.framework.view.TabFragmentTabHost;
//
///**
// * create by Dale
// * create on 2019/5/30
// * description: Tab 基类
// */
//public abstract class ABTabActivity extends ABBaseActivity{
//
//    protected TabFragmentTabHost bottomBar;
//    protected String[] texts;
//    protected TextView[] msgCounts;
//    protected Class<? extends Fragment>[] fragments;
//    protected int [] resIds;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_tab_main;
//    }
//
//    @Override
//    protected void initPresenters() {
//    }
//
//
//    @Override
//    protected void initViewsAndEvents() {
//        texts = getTabTexts();
//        fragments = getFragments();
//        resIds = getTabImageResIds();
//        bottomBar = findViewById(android.R.id.tabhost);
//        bottomBar.setup(mContext, getSupportFragmentManager(), R.id.fragment_content);
//        msgCounts = new TextView[texts.length];
//        for (int i = 0; i < texts.length; i++) {
//            TabHost.TabSpec tabSpec = bottomBar.newTabSpec(texts[i]).setIndicator(getTabItemView(i));
//            bottomBar.addTab(tabSpec, fragments[i], bundle);
//        }
//    }
//
//    protected View getTabItemView(int index) {
//        View view = View.inflate(mContext, R.layout.item_home_tabhost, null);
//        ImageView imageView = view.findViewById(R.id.tabhost_iv);
//        imageView.setImageResource(resIds[index]);
//        TextView textView = view.findViewById(R.id.tabhost_tv);
//        TextView msgCount = view.findViewById(R.id.msgCount);
//        textView.setText(texts[index]);
//        msgCounts[index] = msgCount;
//        return view;
//    }
//
//    protected void setTextMsg(int index, String msgCount){
//        msgCounts[index].setText(msgCount);
//        msgCounts[index].setVisibility(View.VISIBLE);
//    }
//
//    protected void setGoneTextMsg(int index){
//        msgCounts[index].setVisibility(View.GONE);
//    }
//
//    /**
//     * 底部tab选项卡标题数组
//     * @return
//     */
//    protected abstract String[] getTabTexts();
//
//    /**
//     * 底部tab选项卡图标数组
//     * @return
//     */
//    protected abstract int [] getTabImageResIds();
//
//    /**
//     * Fragment数组
//     * @return
//     */
//    protected abstract Class<? extends Fragment>[] getFragments();
//}
