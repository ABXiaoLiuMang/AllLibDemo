package com.dale.framework.ui;


import android.view.View;

import androidx.annotation.CallSuper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.dale.framework.R;
import com.dale.framework.util.GridItemDecoration;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

/**
 * create by Dale
 * create on 2019/5/17
 * description:下拉刷新基类
 */
public abstract class ABRefreshFragment<T,P extends BasePresenter> extends ABBaseFragment<P> implements IRefresh<T> {

    protected RefreshDelegate<T> refreshDelegate;
    protected BaseQuickAdapter<T, BaseViewHolder> listAdapter;
    protected RefreshLayout refreshLayout;
    protected RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_refresh;
    }

    @CallSuper
    @Override
    protected void initViewsAndEvents() {
        refreshDelegate = new RefreshDelegate<>(this);
        listAdapter = getListAdapter();
        refreshDelegate.initViews(listAdapter);
    }

    @Override
    public RefreshLayout getRefreshLayout() {
        refreshLayout = rootView.findViewById(R.id.refreshLayout);
        return refreshLayout;
    }

    @Override
    public RecyclerView getRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recyclerview);
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
                .horColor(R.color.divider_color)
                .verColor(R.color.divider_color)
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
        return null;
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
