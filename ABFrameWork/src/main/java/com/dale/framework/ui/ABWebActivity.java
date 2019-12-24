//package com.dale.framework.ui;
//
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.WebView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.dale.framework.R;
//import com.dale.framework.util.ABConfig;
//import com.dale.framework.view.TitleBar;
//import com.scwang.smartrefresh.layout.api.RefreshLayout;
//
//
///**
// * create by Dale
// * create on 2019/5/17
// * description: webview基类
// */
//public class ABWebActivity extends AbsWebActivity implements TitleBar.LeftBtnOnClickListener,TitleBar.RightBtnOnClickListener{
//    protected TitleBar titleBar;
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_webview;
//    }
//
//    @Override
//    protected void initViewsAndEvents() {
//        String mTitleName = bundle.getString(ABConfig.KEY_TITLE);
//        titleBar = findViewById(R.id.titleBar);
//        titleBar.setTiteTextView(mTitleName);
//        titleBar.setShowRight(View.VISIBLE);
//        titleBar.setRightTextView("关闭");
//        titleBar.setLeftOnClickListener(this);
//        titleBar.setRightOnClickListener(this);
//        buildAgentWeb();
//    }
//
//    @NonNull
//    @Override
//    protected ViewGroup getAgentWebParent() {
//        return findViewById(R.id.webParent);
//    }
//
//    @Nullable
//    @Override
//    protected String getUrl() {
//        return bundle.getString(ABConfig.KEY_TEXT);
//    }
//
//    @Override
//    protected void setTitle(WebView view, String title) {
//        titleBar.setTiteTextView(title);
//    }
//
//    @Override
//    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//        mAgentWeb.getUrlLoader().reload();
//    }
//
//    @Override
//    protected void onProgress(int newProgress) {
//        if(newProgress >= 90){
//            mSmartRefreshLayout.finishRefresh();
//        }
//    }
//
//    @Override
//    public void onLeftClick(View view) {
//        if (!mAgentWeb.back()) {
//            finish();
//        }
//    }
//
//    @Override
//    public void onRightClick(View view) {
//        finish();
//    }
//}
