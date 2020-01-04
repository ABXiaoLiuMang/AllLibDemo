package com.dale.automore;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @HotBitmapGG RecycleView上拉加载更多
 */
public abstract class AutoLoadOnScrollListener extends RecyclerView.OnScrollListener {

    private boolean loading = false;

    int totalItemCount, lastVisibleItem;

    private LinearLayoutManager mLinearLayoutManager;


    public AutoLoadOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }


    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        totalItemCount = mLinearLayoutManager.getItemCount();
        lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        if (!loading && (lastVisibleItem > totalItemCount - 3) && dy > 0) {
            onLoadMore();
            loading = true;
        }
    }


    public abstract void onLoadMore();

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }
}
