package com.dale.xweb.webview;

import android.content.Context;
import android.content.MutableContextWrapper;
import android.os.Looper;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.ArrayList;
import java.util.List;

/**
 * WebView 池
 */
public class XWebViewPool {

    private static XWebViewPool sInstance = null;
    private static List<XWebView> sAvailable;
    private static List<XWebView> sInUse;
    private int mPoolSize = 3;
    // 此context应该是applicationContext，生命周期和APP一致
    private Context mContext;

    private XWebViewPool() {
        sAvailable = new ArrayList<>();
        sInUse = new ArrayList<>();
    }

    public static XWebViewPool getInstance() {
        if (sInstance == null) {
            synchronized (XWebViewPool.class) {
                if (sInstance == null) {
                    sInstance = new XWebViewPool();
                }
            }
        }
        return sInstance;
    }

    public void setMaxSize(int maxSize) {
        mPoolSize = maxSize;
    }

    public void initWebViewPool(Context context) {
        this.mContext = context;
        for (int i = 0; i < mPoolSize; i++) {
            // 引入Context中间层MutableContextWrapper
            XWebView webView = new XWebView(new MutableContextWrapper(mContext));
            sAvailable.add(webView);
        }
    }

    public synchronized XWebView getWebView() {
        XWebView webView;
        if (sAvailable.size() > 0) {
            webView = sAvailable.remove(0);
        } else {
            // 无可用的webview时，自动扩容
            webView = new XWebView(new MutableContextWrapper(mContext));
        }
        sInUse.add(webView);
        webView.loadUrl("");
        return webView;
    }

    public synchronized void resetWebView(XWebView webView) {
        ((MutableContextWrapper) webView.getContext()).setBaseContext(mContext);
        reset(webView);
        sInUse.remove(webView);
        if (sAvailable.size() < mPoolSize) {
            // 保存个数不能大于池子的大小
            sAvailable.add(webView);
        } else {
            // 扩容出来的临时webview直接回收
            destroy(webView);
        }
    }

    /**
     * 重置WebView
     */
    protected void reset(XWebView webView) {
        if (webView != null) {
            webView.stopLoading();
            webView.clearCache(true);
            webView.clearHistory();
        }
    }

    /**
     * 销毁WebView
     */
    protected void destroy(XWebView webView) {
        try {
            if (webView != null) {
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    return;
                }
                webView.loadUrl("about:blank");
                webView.stopLoading();
                if (webView.getHandler() != null) {
                    webView.getHandler().removeCallbacksAndMessages(null);
                }
                webView.clearCache(true);
                webView.clearHistory();
                ViewParent parent = webView.getParent();
                if (parent != null) {
                    ((ViewGroup) parent).removeView(webView);
                }
                webView.removeAllViews();
                webView.setWebChromeClient(null);
                webView.setWebViewClient(null);
                webView.setTag(null);
                webView.clearHistory();
                webView.destroy();
                webView = null;
            }
        } catch (Exception e) {
            // ignore
            e.printStackTrace();
        }
    }

}