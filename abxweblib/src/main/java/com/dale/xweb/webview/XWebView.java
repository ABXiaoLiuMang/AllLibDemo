package com.dale.xweb.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * XWebView支持普通的h5和游戏的h5
 */
public class XWebView extends WebView {
    //isTop的判断
    private boolean isTop = false;

    public XWebView(Context context) {
        this(context,null);
    }

    public XWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSetting();
    }

    public XWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSetting();
    }

    @SuppressLint({"ClickableViewAccessibility", "SetJavaScriptEnabled"})
    private void initSetting() {
//        compatAndroidP();
        WebSettings settings = getSettings();
        settings.setEnableSmoothTransition(false);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);

        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setBlockNetworkImage(false);
        settings.setBlockNetworkLoads(false);
        settings.setBuiltInZoomControls(false);
        settings.setDatabaseEnabled(true);


        //支持本地缓存
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setJavaScriptEnabled(true);
        settings.setLightTouchEnabled(false);
        settings.setLoadWithOverviewMode(true);
        //是否启用预览模式加载界面

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }

        settings.setUseWideViewPort(true);//图片适配WebView
        settings.setAppCacheEnabled(true);//启用缓存
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setSupportZoom(true);
        settings.setDisplayZoomControls(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        CookieManager.getInstance().setAcceptCookie(true);
        settings.setMediaPlaybackRequiresUserGesture(true);
    }


    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if (scrollY <= 0) {
            if (!isTop && deltaY < 0) {
                isTop = true;
            }
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public boolean isTop() {
        return isTop;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isTop = false;
                if (this.getScrollY() <= 0)
                    this.scrollTo(0, 1);
                break;
            case MotionEvent.ACTION_UP:
                isTop = false;
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

}