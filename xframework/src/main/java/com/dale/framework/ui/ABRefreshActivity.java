package com.dale.framework.ui;


import android.view.View;

import androidx.annotation.CallSuper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.dale.framework.R;
import com.dale.framework.util.GridItemDecoration;
import com.dale.framework.view.StateLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

/**
 * create by Dale
 * create on 2019/5/17
 * description:下拉刷新基类
 */
public abstract class ABRefreshActivity<T,P extends BasePresenter> extends ABBaseActivity<P> implements IRefresh<T>, StateLayout.OnRetryListener {

    protected RefreshDelegate<T> refreshDelegate;
    protected BaseQuickAdapter<T, BaseViewHolder> listAdapter;
    protected RefreshLayout refreshLayout;
    protected RecyclerView recyclerView;
    protected StateLayout stateLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.x_activity_refresh;
    }

    @CallSuper
    @Override
    protected void initViewsAndEvents() {
//        if(presenter != null){
//            childPresenter = presenter;
//        }
        refreshDelegate = new RefreshDelegate<>(this);
        listAdapter = getListAdapter();
        refreshDelegate.initViews(listAdapter);
    }

    @Override
    public RefreshLayout getRefreshLayout() {
        refreshLayout = findViewById(R.id.refreshLayout);
        return refreshLayout;
    }

    @Override
    public RecyclerView getRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview);
        return recyclerView;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    public RecyclerView.ItemDecoration getItemDecoration() {
        return new GridItemDecoration
                .Builder(mContext)
                .horColor(R.color.x_line_color)
                .verColor(R.color.x_line_color)
                .showLastDivider(true)
                .size(1)
                .build();
    }

    @Override
    public int getMode() {
        return Mode.BOTH;
    }


    @Override
    public View getEmptyView() {
        stateLayout = (StateLayout) View.inflate(mContext,R.layout.x_statelayout,null);
        stateLayout.contentView((SmartRefreshLayout) refreshLayout);
        stateLayout.setOnRetryListener(this);
        return stateLayout;
    }



    @Override
    public void onRetry(){
        stateLayout.setState(StateLayout.STATE_LOADING);
        onRefresh(refreshLayout);
    }



    @Override
    public View getHeaderView() {
        return null;
    }

    @Override
    public View getFooterView() {
        return null;
    }

}
