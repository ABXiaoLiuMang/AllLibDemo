package com.dale.refresh;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.framework.ui.ABBaseFragment;
import com.dale.libdemo.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RefreshTestActivity extends ABBaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_refresh;
    }

    @Override
    protected void createProvider() {

    }

    TestAdapter testAdapter;
    @Override
    protected void initViewsAndEvents() {
        recyclerview.setLayoutManager(new GridLayoutManager(mContext,10));
        testAdapter =new TestAdapter(R.layout.item_test);
        recyclerview.setAdapter(testAdapter);

        List<String> arrayList = new ArrayList();
        for(int i = 0;i < 500; i++){
            arrayList.add("dd");
        }
        testAdapter.setNewData(arrayList);
    }


    class TestAdapter extends BaseQuickAdapter<String, BaseViewHolder>{

        public TestAdapter(int layoutResId) {
            super(layoutResId);
        }


        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}
