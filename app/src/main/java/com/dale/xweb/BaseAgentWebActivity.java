package com.dale.xweb;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dale.framework.ui.ABBaseActivity;
import com.dale.framework.view.TitleBar;
import com.dale.libdemo.R;
import com.dale.utils.Keyboard;
import com.dale.xweb.webview.XWebView;


public abstract class BaseAgentWebActivity extends ABBaseActivity {
    protected TitleBar titleBar;
    protected XWebViewManager xWebViewManager;
    protected View xLoadingView;
    protected XWebView xWebView;

    protected abstract @Nullable String getUrl();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_h5;
    }


    @Override
    protected void initViewsAndEvents() {
        Keyboard.assistActivity(this);
        titleBar = findViewById(R.id.titleBar);
        xWebViewManager = XWebViewManager.getInstance();
        xWebViewManager.setErrorLayout(R.layout.web_error_page, R.id.tvRefresh);
        xLoadingView = getLoadingView();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        xWebView = xWebViewManager.startXWebView(this, getAgentWebParent(), layoutParams, getUrl(), xLoadingView);
    }


    @NonNull
    protected ViewGroup getAgentWebParent() {
        return findViewById(R.id.layout);
    }

    @NonNull
    protected View getLoadingView() {
        return findViewById(R.id.loadingView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (xWebViewManager.back()) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        xWebViewManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        xWebViewManager.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        xWebViewManager.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        xWebViewManager.onActivityResult(requestCode, resultCode, data);
    }
}
