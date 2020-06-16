package com.dale.xweb.delegate;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.dale.xweb.webview.XWebView;


public interface XWebDelegate {
    //初始化
    void init(Application application);
    //跟随 Activity Or Fragment 生命周期 ， 释放 CPU 更省电
    void onPause();
    void onResume();
    void onDestroy();

    //创建AgentWeb

    /**
     *
     * @param activity 当前的界面
     * @param viewGroup WebView依赖的父布局
     * @param lp 布局参数：
     * @param url 加载的地址
     * @param loadingView loadingView:支持ProgressBar，支持SwipeRefreshLayout，以及其他的可以通过显示和隐藏的loading
     */
    XWebView createAgentWeb(Activity activity, ViewGroup viewGroup, ViewGroup.LayoutParams lp, String url, View loadingView);

    /**
     *
     * @param activity 当前的界面
     * @param xWebView 其他
     * @param viewGroup WebView依赖的父布局
     * @param lp 布局参数：
     * @param url 加载的地址
     * @param loadingView loadingView:支持ProgressBar，支持SwipeRefreshLayout，以及其他的可以通过显示和隐藏的loading
     */
    XWebView createAgentWeb(Activity activity, XWebView xWebView, ViewGroup viewGroup, ViewGroup.LayoutParams lp, String url, View loadingView);

    /**
     *
     * @param activity 当前的界面
     * @param viewGroup WebView依赖的父布局
     * @param lp 布局参数：
     * @param html html
     * @param loadingView loadingView:支持ProgressBar，支持SwipeRefreshLayout，以及其他的可以通过显示和隐藏的loading
     */
    XWebView createGameAgentWeb(Activity activity, ViewGroup viewGroup, ViewGroup.LayoutParams lp, String html, View firstLoadingView, View loadingView);

    /**
     * 返回
     * @return  true 可以返回，false表示WebView展示的就是 最上层的界面
     */
    boolean back();


    /**
     * Activity的回调
     * @param requestCode 请求码
     * @param resultCode 结果码
     * @param data 数据
     */
    void onActivityResult(int requestCode, int resultCode, Intent data);


    /**
     * 设置错误布局
     * @param errorLayout 错误布局
     */
    void setErrorLayout(View errorLayout);


    /**
     * 设置错误布局
     * @param errorLayoutId 错误布局
     * @param clickId 点击重新加载控件id
     */
    void setErrorLayout(int errorLayoutId, int clickId);
}

