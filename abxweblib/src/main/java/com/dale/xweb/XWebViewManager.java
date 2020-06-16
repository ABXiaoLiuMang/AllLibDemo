package com.dale.xweb;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.dale.xweb.delegate.XWebDelegate;
import com.dale.xweb.delegate.XWebDelegateImpl;
import com.dale.xweb.webview.XWebView;

/**
 * XWeb的管理类
 */
public class XWebViewManager {
    private static final XWebViewManager ourInstance = new XWebViewManager();

    public static XWebViewManager getInstance() {
        return ourInstance;
    }

    private final XWebDelegate xWebDelegate;
    /**
     * web的进程名字
     */
    private String webProcessName;

    public String getWebProcessName() {
        return webProcessName;
    }

    /**
     * 设置XWeb的进程名：全称
     *
     * @param webProcessName XWeb的进程名：全称
     * @return
     */
    public XWebViewManager setWebProcessName(String webProcessName) {
        this.webProcessName = webProcessName;
        return this;
    }

    private XWebViewManager() {
        xWebDelegate = new XWebDelegateImpl();
    }

    /**
     * 初始化
     *
     * @param application 上下文
     */
    public void init(Application application) {
        xWebDelegate.init(application);
    }


    //跟随 Activity Or Fragment 生命周期 ， 释放 CPU 更省电
    public void onPause() {
        xWebDelegate.onPause();
    }

    public void onResume() {
        xWebDelegate.onResume();
    }

    public void onDestroy() {
        xWebDelegate.onDestroy();
    }

    /**
     * 启动XWebView
     * @param activity 当前的界面
     * @param viewGroup WebView依赖的父布局
     * @param lp 布局参数：
     * @param url 加载的地址
     * @param loadingView loadingView:支持ProgressBar
     */
    public XWebView startXWebView(Activity activity, ViewGroup viewGroup, ViewGroup.LayoutParams lp, String url, View loadingView) {
        return xWebDelegate.createAgentWeb(activity, viewGroup, lp, url, loadingView);
    }

    /**
     * 启动XWebView
     * @param activity 当前的界面
     * @param xWebView 布局中的XWebView
     * @param viewGroup WebView依赖的父布局
     * @param lp 布局参数：
     * @param url 加载的地址
     * @param loadingView loadingView:支持ProgressBar
     */
    public XWebView startXWebView(Activity activity,XWebView xWebView, ViewGroup viewGroup, ViewGroup.LayoutParams lp, String url, View loadingView) {
        return xWebDelegate.createAgentWeb(activity,xWebView, viewGroup, lp, url, loadingView);
    }


    /**
     * @param activity 当前的界面
     * @param viewGroup WebView依赖的父布局
     * @param lp 布局参数：
     * @param html html
     * @param firstLoadingView loadingView:支持ProgressBar
     * @param loadingView loadingView:支持ProgressBar
     */
    public XWebView startGameXWebView(Activity activity, ViewGroup viewGroup, ViewGroup.LayoutParams lp, String html, View firstLoadingView, View loadingView) {
        return xWebDelegate.createGameAgentWeb(activity, viewGroup, lp, html, firstLoadingView,loadingView);
    }



    /**
     * 返回
     * @return  true WebView可以返回，false表示WebView展示的就是 最上层的界面
     */
    public boolean back(){
        return xWebDelegate.back();
    }

    /**
     * Activity的回调 目前用于图片选择之后的处理
     * @param requestCode 请求码
     * @param resultCode 结果码
     * @param data 数据
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        xWebDelegate.onActivityResult(requestCode, resultCode, data);
    }


    public void setErrorLayout(View errorLayout){
        xWebDelegate.setErrorLayout(errorLayout);
    }
    public void setErrorLayout(int errorLayoutId,int clickId){
        xWebDelegate.setErrorLayout(errorLayoutId,clickId);
    }

}
