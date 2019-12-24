//package com.dale.framework.ui;
//
//import android.view.View;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.scwang.smartrefresh.layout.api.RefreshLayout;
//
//import static com.dale.framework.ui.Mode.BOTH;
//import static com.dale.framework.ui.Mode.DISABLED;
//import static com.dale.framework.ui.Mode.PULL_FROM_END;
//import static com.dale.framework.ui.Mode.PULL_FROM_START;
//
//public class RefreshDelegate<T> {
//
//    private IRefresh iRefresh;
//
//    public RefreshDelegate(IRefresh iRefresh) {
//        this.iRefresh = iRefresh;
//    }
//
//    public void initViews(BaseQuickAdapter<T, BaseViewHolder> listAdapter) {
//        RecyclerView recyclerview = iRefresh.getRecyclerView();
//        RefreshLayout refreshLayout = iRefresh.getRefreshLayout();
//        switch (iRefresh.getMode()) {
//            case PULL_FROM_END://上拉加载更多
//                refreshLayout.setOnLoadMoreListener(iRefresh);
//                refreshLayout.setEnableRefresh(false);
//                refreshLayout.setEnableLoadMore(true);
//                break;
//            case PULL_FROM_START://顶部下拉刷新
//                refreshLayout.setOnRefreshListener(iRefresh);
//                refreshLayout.setEnableRefresh(true);
//                refreshLayout.setEnableLoadMore(false);
//                break;
//            case BOTH://上下都刷新
//                refreshLayout.setOnRefreshListener(iRefresh);
//                refreshLayout.setOnLoadMoreListener(iRefresh);
//                break;
//            case DISABLED://上下都不刷新
//                refreshLayout.setEnableLoadMore(false);
//                refreshLayout.setEnableRefresh(false);
//                break;
//        }
//
//         View headView;
//         View fooderView;
//         View emptyView;
//
//        if ((emptyView = getEmptyView()) != null) {
//            listAdapter.setEmptyView(emptyView);
//        }
//        if ((headView = getHeaderView()) != null) {
//            listAdapter.addHeaderView(headView);
//        }
//        if ((fooderView = getFooterView()) != null) {
//            listAdapter.addFooterView(fooderView);
//        }
//        refreshLayout.setEnableFooterFollowWhenNoMoreData(true);
//        listAdapter.setOnItemClickListener(iRefresh);
//        recyclerview.setLayoutManager(getLayoutManager());
//        RecyclerView.ItemDecoration itemDecoration = getItemDecoration();
//        if (itemDecoration != null) {
//            recyclerview.addItemDecoration(itemDecoration);
//        }
//        recyclerview.setAdapter(listAdapter);
//    }
//
//    private RecyclerView.LayoutManager getLayoutManager() {
//        return iRefresh.getLayoutManager();
//    }
//
//    private RecyclerView.ItemDecoration getItemDecoration() {
//        return iRefresh.getItemDecoration();
//    }
//
//
//    private View getEmptyView() {
//        return iRefresh.getEmptyView();
//    }
//
//    private View getHeaderView() {
//        return iRefresh.getHeaderView();
//    }
//
//    private View getFooterView() {
//        return iRefresh.getFooterView();
//    }
//
//}
