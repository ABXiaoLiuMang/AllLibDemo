package com.dale.automore;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dale.framework.ui.ABBaseActivity;
import com.dale.framework_demo.PersonAdapter;
import com.dale.libdemo.R;
import com.dale.utils.WeakHandler;

import java.util.ArrayList;

public class AutoLoadActivity extends ABBaseActivity {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;

    PersonAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private AutoLoadOnScrollListener mAutoLoadOnScrollListener;
    int page = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_autoload;
    }

    @Override
    protected void initViewsAndEvents() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh);
        initSwipeRefreshLayout();
        initRecyclerView();
    }

    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.black);
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
        initTest(page);
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            page = 1;
            initTest(page);
        });
    }

    private void initRecyclerView() {
        mAdapter = new PersonAdapter(R.layout.item_layout);
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAutoLoadOnScrollListener = new AutoLoadOnScrollListener(mLinearLayoutManager) {
            @Override
            public void onLoadMore() {
                page++;
                initTest(page);
            }


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mSwipeRefreshLayout.setEnabled(
                        mLinearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
            }
        };
        mRecyclerView.addOnScrollListener(mAutoLoadOnScrollListener);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initTest(int page){
        new WeakHandler().postDelayed(() -> {
            if(page == 1){
                mAdapter.setNewData(initdata());
            }else {
                mAdapter.addData(initdata());
            }
            mAutoLoadOnScrollListener.setLoading(false);
            mSwipeRefreshLayout.setRefreshing(false);
        },500);
    }


    private ArrayList<String> initdata(){
        ArrayList<String> arrList = new ArrayList();
        for (int i = 0; i < 16; i++) {
            arrList.add("gogo--:" + i);
        }
        return arrList;
    }

}
