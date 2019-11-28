package com.dale.adapter;


import android.util.TypedValue;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.libdemo.R;
import com.dale.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class MarqueAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private List<String> originList = null;

    public MarqueAdapter() {
        super(R.layout.item_marque);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView scrollView = helper.getView(R.id.tv_scroll_content);
        scrollView.setText((helper.getLayoutPosition()%originList.size() + 1) +"、" +item);
        scrollView.setPadding(ScreenUtils.getScreenWidth() / 4, 0, 0, 0);
    }

    @Override
    public void setNewData(@Nullable List<String> data) {
        if (data != null){
            originList = new ArrayList<>(data);
        }
        super.setNewData(data);
    }

    public void reload() {
        List<String> stringList = new ArrayList<>(originList);
        addData(stringList);
    }
}

