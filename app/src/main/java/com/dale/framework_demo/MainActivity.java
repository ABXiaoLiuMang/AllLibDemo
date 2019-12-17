package com.dale.framework_demo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.constant.PermissionConstants;
import com.dale.framework.ui.ABRefreshActivity;
import com.dale.framework.ui.ABWebActivity;
import com.dale.framework.util.ABConfig;
import com.dale.framework.util.ScrollChangeHelper;
import com.dale.framework.view.TitleBar;
import com.dale.framework_demo.ui.KeyValueActivity;
import com.dale.framework_demo.ui.MainContract;
import com.dale.framework_demo.ui.MainPresenter;
import com.dale.framework_demo.ui.OtherPresenter;
import com.dale.framework_demo.ui.ScrollActivity;
import com.dale.libdemo.R;
import com.dale.net.callback.NetObserver;
import com.dale.net.exception.ErrorMessage;
import com.dale.utils.LogUtils;
import com.dale.utils.PermissionUtils;
import com.dale.utils.SizeUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;


public class MainActivity extends ABRefreshActivity<String> implements MainContract.IView {

    MainPresenter mainPresenter;
    OtherPresenter otherPresenter;

    int page = 0;
    protected NestedScrollView scrollView;
    protected TitleBar titleBar;
    protected TextView tv_head;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_alpha_refresh;
    }

    @Override
    protected void initPresenters() {
        mainPresenter = new MainPresenter(this, this);
        otherPresenter = new OtherPresenter(this);

    }


    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        scrollView = findViewById(com.dale.framework.R.id.scrollView);
        titleBar = findViewById(com.dale.framework.R.id.titleBar);
        new ScrollChangeHelper.Builder().scrollHeight(SizeUtils.dp2px(150)).setAlphaView(titleBar).setNestedScrollView(scrollView).build();

//        goActivity(ScrollActivity.class);
        mainPresenter.initRequest();

        LiveDataManager.getInstance().testPrice.observe(this,new NetObserver<String>(){
            @Override
            protected void onSuccess(String s) {
                 tv_head.setText("-->" + s);
                LogUtils.d("MainActivity:" + s);
                dismissLoading();
            }

            @Override
            protected void onError(ErrorMessage errorMessage) {
                tv_head.setText("-->" + errorMessage.getMessage());
                dismissLoading();
            }
        });

        otherPresenter.testOther();
    }

    @Override
    public BaseQuickAdapter<String, BaseViewHolder> getListAdapter() {
        return new PersonAdapter(R.layout.item_layout);
    }

/*    @Override
    protected View getEmptyView() {
        return View.inflate(mContext,R.layout.empty_layout,null);
    }*/

    @Override
    public View getHeaderView() {
        View headView = View.inflate(mContext, R.layout.header_layout, null);
        tv_head = headView.findViewById(R.id.tv_head);
        headView.setOnClickListener(v -> {
            showLoading();
            otherPresenter.testOther();
        });
        return headView;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position % 4) {
            case 0:
                Bundle bundle = new Bundle();
                bundle.putString(ABConfig.KEY_TITLE, "测试web");
                bundle.putString(ABConfig.KEY_TEXT, "https://123.sogou.com/");
                goActivity(ABWebActivity.class, bundle);
                break;
            case 1:
                goActivity(KeyValueActivity.class);
                break;
            case 2:
                goActivity(ScrollActivity.class);
                break;
            case 3:
//                goActivity(TabMYBaseActivity.class);
                break;
        }
//
//
//

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        if (page >= 4) {
            refreshLayout.finishLoadMoreWithNoMoreData();
        } else {
            page++;
            mainPresenter.onLoadMore(page);
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 0;
        mainPresenter.onRefresh();
    }


    @Override
    public void initSuccess(List<String> list) {
        listAdapter.setNewData(list);
        refreshLayout.finishRefresh();
    }

    @Override
    public void onLoadMoreSuccess(List<String> list) {
        listAdapter.addData(list);
        refreshLayout.finishLoadMore();
    }

    @Override
    public void ononRefreshSuccess(List<String> list) {
        listAdapter.setNewData(list);
        refreshLayout.finishRefresh();
    }


    @Override
    public void showLoading() {
        showProgressDialog();
    }

    @Override
    public void dismissLoading() {
        dismissProgressDialog();
    }
}
