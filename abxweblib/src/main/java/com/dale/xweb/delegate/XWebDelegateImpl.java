package com.dale.xweb.delegate;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.dale.xweb.XWebViewManager;
import com.dale.xweb.cache.CacheConfig;
import com.dale.xweb.cache.CacheExtConfig;
import com.dale.xweb.cache.CacheType;
import com.dale.xweb.ui.XLoading;
import com.dale.xweb.webview.XWebChromeDefaultClient;
import com.dale.xweb.webview.XWebView;
import com.dale.xweb.webview.XWebViewCacheSDK;
import com.dale.xweb.webview.XWebViewDefaultClient;
import com.dale.xweb.webview.XWebViewPool;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static android.view.View.OVER_SCROLL_NEVER;

public class XWebDelegateImpl implements XWebDelegate {

    private boolean isInit = false;

    @Override
    public void init(Application application) {
        if (isInit) {
            Log.d("在XWeb进程中:", "XWeb已经初始化");
            return;
        }
        isInit = true;
        String webProcessName = XWebViewManager.getInstance().getWebProcessName();
        if (TextUtils.isEmpty(webProcessName)) {
            throw new IllegalArgumentException("请先调用setWebProcessName设置XWeb的进程名");
        }


        String currentProcessName = getCurrentProcessName();

        //当为web的进程时 初始化
        if (TextUtils.equals(currentProcessName, webProcessName)) {
            Log.d("在XWeb进程中:", "初始化");
            compatAndroidP(application);
            XWebViewPool.getInstance().initWebViewPool(application.getApplicationContext());
            initXWebCache(application);


        } else {
            Log.d("XWeb在主进程中:", "啥事也没做");
        }
    }

    /**
     * 因为Android P行为变更，不可多进程使用同一个目录webView，需要为不同进程webView设置不同目录
     */
    private void compatAndroidP(Application application) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && !isMainProcess(application)) {
            XWebView.setDataDirectorySuffix(application.getApplicationInfo().processName);
        }
    }

    public static boolean isMainProcess(Context app) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) app.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return app.getApplicationInfo().packageName.equals(appProcess.processName);
            }
        }
        return false;
    }

    @Override
    public void onPause() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onPause();
        }
    }

    @Override
    public void onResume() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onResume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAgentWeb != null) {
            xWebChromeDefaultClient = null;
            mAgentWeb.getWebLifeCycle().onDestroy();
            mAgentWeb = null;
        }
    }

    private AgentWeb mAgentWeb;
    private XWebChromeDefaultClient xWebChromeDefaultClient;

    @Override
    public XWebView createAgentWeb(Activity activity, ViewGroup viewGroup, ViewGroup.LayoutParams lp, String url, final View loadingView) {
        //从缓存池中获取WebView 然后设置WebView，有复用
        //如果不调用setWebView就是用AgentWeb内部的 没有复用
//        final XWebView webView = XWebViewPool.getInstance().getWebView();
//        final XWebView webView = new XWebView(new MutableContextWrapper(activity.getApplication()));
        XWebView webView = new XWebView(activity);
        webView.setOverScrollMode(OVER_SCROLL_NEVER);
        webView.setVerticalFadingEdgeEnabled(false);
        webView.setHorizontalFadingEdgeEnabled(false);
        xWebChromeDefaultClient = new XWebChromeDefaultClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        };

        mAgentWeb = AgentWeb.with(activity)//传入Activity
                .setAgentWebParent(viewGroup, 0, lp)
                .closeIndicator()
                .setWebView(webView) // 传入自己构造的webview比如说webview复用池里面的对象
                .setWebChromeClient(xWebChromeDefaultClient)
                .setMainFrameErrorView(errorLayout)
                .setMainFrameErrorView(errorLayoutId, clickId)
                .setWebViewClient(new XWebViewDefaultClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        loadingView.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        loadingView.setVisibility(View.VISIBLE);
                    }
                })

//                .defaultProgressBarColor() // 使用默认进度条颜色
//                .setReceivedTitleCallback(null) // 设置 Web 页面的 title 回调
                .createAgentWeb()
                .ready()
                .go(url);
        return webView;

    }


    @Override
    public XWebView createAgentWeb(Activity activity, final XWebView webView, ViewGroup viewGroup, ViewGroup.LayoutParams lp, String url, final View loadingView) {
        webView.setOverScrollMode(OVER_SCROLL_NEVER);
        webView.setVerticalFadingEdgeEnabled(false);
        webView.setHorizontalFadingEdgeEnabled(false);
        //index为0

        xWebChromeDefaultClient = new XWebChromeDefaultClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        };

        mAgentWeb = AgentWeb.with(activity)//传入Activity
                .setAgentWebParent(viewGroup, 0, lp)
                .setCustomIndicator(new XLoading(activity)) // 使用默认进度条
                .setWebView(webView) // 传入自己构造的webview比如说webview复用池里面的对象
                .setMainFrameErrorView(errorLayout)
                .setWebChromeClient(xWebChromeDefaultClient)
                .setWebViewClient(new XWebViewDefaultClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        loadingView.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        loadingView.setVisibility(View.VISIBLE);
                    }
                })
//                .defaultProgressBarColor() // 使用默认进度条颜色
//                .setReceivedTitleCallback(null) // 设置 Web 页面的 title 回调
                .createAgentWeb()
                .ready()
                .go(url);

        return webView;
    }

    private boolean isFirstLoading = true;

    @Override
    public XWebView createGameAgentWeb(Activity activity, ViewGroup viewGroup, ViewGroup.LayoutParams lp, String html, final View firstLoadingView, final View loadingView) {
        final XWebView webView = XWebViewPool.getInstance().getWebView();
        webView.setOverScrollMode(OVER_SCROLL_NEVER);
        webView.setVerticalFadingEdgeEnabled(false);
        webView.setHorizontalFadingEdgeEnabled(false);
        //index为0 首次进来 重置
        isFirstLoading = true;
        xWebChromeDefaultClient = new XWebChromeDefaultClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        };

        mAgentWeb = AgentWeb.with(activity)//传入Activity
                .setAgentWebParent(viewGroup, 0, lp)
                .setCustomIndicator(new XLoading(activity)) // 使用默认进度条
                .setWebView(webView) // 传入自己构造的webview比如说webview复用池里面的对象
                .setWebChromeClient(xWebChromeDefaultClient)
                .setWebViewClient(new XWebViewDefaultClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        if (isFirstLoading) {
                            firstLoadingView.setVisibility(View.GONE);
                        } else {
                            loadingView.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        if (isFirstLoading) {
                            firstLoadingView.setVisibility(View.VISIBLE);
                        } else {
                            loadingView.setVisibility(View.VISIBLE);
                        }
                    }

                })
//                .defaultProgressBarColor() // 使用默认进度条颜色
//                .setReceivedTitleCallback(null) // 设置 Web 页面的 title 回调
                .createAgentWeb()
                .ready().go(html);

        mAgentWeb.getUrlLoader().loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        return webView;
    }

    @Override
    public boolean back() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebCreator().getWebView().getOriginalUrl();
            return mAgentWeb.back();
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (xWebChromeDefaultClient != null) {
            xWebChromeDefaultClient.onActivityResult(requestCode, resultCode, data);
        }
    }

    private View errorLayout;
    private int errorLayoutId;
    private int clickId;

    @Override
    public void setErrorLayout(View errorLayout) {
        this.errorLayout = errorLayout;
    }

    @Override
    public void setErrorLayout(int errorLayoutId, int clickId) {
        this.errorLayoutId = errorLayoutId;
        this.clickId = clickId;
    }


    /**
     * 获取当前进程名
     *
     * @return
     */
    private String getCurrentProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //初始化WebView的缓存
    private void initXWebCache(Application application) {

        CacheExtConfig cacheExtConfig = new CacheExtConfig();
        // 如果不需要默认，设置可以先清除默认设置
        cacheExtConfig.addExt("webp").addExt("png").addExt("jpg").addExt("jpeg").addExt("gif").addExt("bmp");

        CacheConfig cacheConfig = new CacheConfig.Builder(application.getApplicationContext())
                .setCachePath(new File(application.getCacheDir(), "cache_path_name"))// 设置缓存路径，默认getCacheDir，名称 CacheWebViewCache
                .setCacheSize(1024 * 1024 * 100) // 设置缓存大小，默认100M
                .setConnectTimeoutSecond(20)// 设置http请求链接超时，默认20秒
                .setReadTimeoutSecond(20)  // 设置http请求链接读取超时，默认20秒
                .setCacheType(CacheType.FORCE)
                .setDebug(true)
                .setCacheExtConfig(cacheExtConfig)
                .setResourceInterceptor(url -> {
                    return true;// 按照默认规则，false 不拦截资源
                })
                .build();

        XWebViewCacheSDK.init(cacheConfig);
    }


    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress > 90) {
//                mWebViewClient.onPageFinished(view, view.getUrl());
//                xLoadingView.stop();
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            //设置标题
        }
    };
}

