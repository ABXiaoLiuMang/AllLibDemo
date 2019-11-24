package com.dale.framework.ui;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.dale.framework.R;
import com.dale.framework.view.SmartRefreshWebLayout;
import com.dale.utils.LogUtils;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.AgentWebSettingsImpl;
import com.just.agentweb.AgentWebUIControllerImplBase;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.IAgentWebSettings;
import com.just.agentweb.IWebLayout;
import com.just.agentweb.MiddlewareWebChromeBase;
import com.just.agentweb.MiddlewareWebClientBase;
import com.just.agentweb.PermissionInterceptor;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.just.agentweb.sonic.SonicImpl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * 抽象webview基类
 */
public abstract class AbsWebActivity extends ABBaseActivity implements OnRefreshListener {

    public static final String ERROR_LAYOUT_ID_KEY = "error_layout_id_key";

    protected AgentWeb mAgentWeb;
    protected SmartRefreshWebLayout mSmartRefreshWebLayout;
    protected SmartRefreshLayout mSmartRefreshLayout;
    protected MiddlewareWebChromeBase mMiddleWareWebChrome;
//    protected MiddlewareWebClientBase mMiddleWareWebClient;
    protected SonicImpl mSonicImpl;

    @Override
    protected void initPresenters() {
    }


    protected void buildAgentWeb() {
        // 1. 首先创建SonicImpl
        mSonicImpl = new SonicImpl(getUrl(),this);
        // 2. 调用 onCreateSession
        mSonicImpl.onCreateSession();
//        mAgentWeb.getJsInterfaceHolder().addJavaObject("sonic", new SonicJavaScriptInterface())

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(getAgentWebParent(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator(getIndicatorColor(), getIndicatorHeight())
//                .closeIndicator()
                .setWebChromeClient(getWebChromeClient())
                .setWebViewClient(getWebViewClient())
                .setWebView(getWebView())
                .setPermissionInterceptor(getPermissionInterceptor())
                .setWebLayout(getWebLayout())
                .setAgentWebUIController(getAgentWebUIController())
                .interceptUnkownUrl()
                .setOpenOtherPageWays(getOpenOtherAppWay())
                .useMiddlewareWebChrome(getMiddleWareWebChrome())
                .useMiddlewareWebClient(getMiddleWareWebClient())
                .setAgentWebWebSettings(getAgentWebSettings())
                .setMainFrameErrorView(getErrorLayoutID(), R.id.tvRefresh)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .createAgentWeb()
                .ready()
                .go(getUrl());
        mSmartRefreshLayout = (SmartRefreshLayout) this.mSmartRefreshWebLayout.getLayout();
        mSmartRefreshLayout.setEnableLoadMore(false);
        mSmartRefreshLayout.setOnRefreshListener(this);
        mSmartRefreshLayout.autoRefresh();

        mSonicImpl.bindAgentWeb(mAgentWeb);
    }


    protected int getErrorLayoutID() {
        int id = bundle.getInt(ERROR_LAYOUT_ID_KEY, -1);
        if (id == -1) {
            id = R.layout.web_error_page;
        }
        return id;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb != null && mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPause() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();

    }

    @Override
    public void onResume() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (mAgentWeb != null) {
//            mAgentWeb.getWebCreator().getWebView().removeCallbacks(timeOutRunnable);
            mAgentWeb.getWebLifeCycle().onDestroy();
        }

        if(mSonicImpl !=null){
            mSonicImpl.destrory();
        }
        super.onDestroy();
    }

    /**
     * @return 加载的url
     */
    protected @Nullable String getUrl() {
        return null;
    }

    public @Nullable IAgentWebSettings getAgentWebSettings() {
        return AgentWebSettingsImpl.getInstance();
    }

    protected abstract @NonNull ViewGroup getAgentWebParent();

    protected @Nullable WebChromeClient getWebChromeClient() {
        return null;
    }

    /**
     * @return 返回进度条颜色
     */
    protected @ColorInt int getIndicatorColor() {
        return -1;
    }

    /**
     * @return 返回进度条高度(会自动转换为dp)
     */
    protected int getIndicatorHeight() {
        return 2;
    }


//    //开始超时计时
//    private boolean isStarted = false;
//
//    private void startTimeOutCount() {
//        if (!isStarted) {
//            isStarted = true;
//            mAgentWeb.getWebCreator().getWebView().postDelayed(timeOutRunnable, 15 * 1000);
//        }
//    }
//
//    private Runnable timeOutRunnable = () -> {
//        if (isStarted) {
//            hideRefreshLoading();
//            isStarted = false;
//        }
//    };

    protected @Nullable WebViewClient getWebViewClient() {
        return new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                showProgressDialog();
//                startTimeOutCount();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                dismissProgressDialog();
//                isStarted = false;
                super.onPageFinished(view, url);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                String des = (String) error.getDescription();
            }


            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }

            @Override
            public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (!detail.didCrash()) {
                        Log.e("webview", "System killed the WebView rendering process " +
                                "to reclaim memory. Recreating...");
                        if (view != null) {
                            ((ViewGroup) view.getParent()).removeView(view);
                            view.destroy();
                            view = null;
                        }
                        return true;
                    }
                    return false;
                } else {
                    return super.onRenderProcessGone(view, detail);
                }
            }

        };
    }

    protected @Nullable WebView getWebView() {
        return null;
    }

    protected @Nullable IWebLayout getWebLayout() {
        return this.mSmartRefreshWebLayout = new SmartRefreshWebLayout(this);
    }

    protected @Nullable PermissionInterceptor getPermissionInterceptor() {
        return null;
    }

    public @Nullable AgentWebUIControllerImplBase getAgentWebUIController() {
        return null;
    }

    public @Nullable DefaultWebClient.OpenOtherPageWays getOpenOtherAppWay() {
        return null;
    }

    protected @NonNull MiddlewareWebChromeBase getMiddleWareWebChrome() {
        return this.mMiddleWareWebChrome = new MiddlewareWebChromeBase() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(view, title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                onProgress(newProgress);
            }
        };
    }

    protected void setTitle(WebView view, String title) {
    }

    protected void onProgress(int newProgress) {
    }

    protected @NonNull MiddlewareWebClientBase getMiddleWareWebClient() {
        return mSonicImpl.createSonicClientMiddleWare();
//        return this.mMiddleWareWebClient = new MiddlewareWebClientBase() {};
    }
}
