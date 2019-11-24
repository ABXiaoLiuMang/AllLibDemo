package com.dale.popup_demo.custom;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.libdemo.R;
import com.lxj.xpopup.core.DrawerPopupView;

import java.util.ArrayList;

/**
 * Description:
 * Create by dance, at 2019/1/9
 */
public class ListDrawerPopupView extends DrawerPopupView {
    RecyclerView recyclerView;
    public ListDrawerPopupView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_list_drawer;
    }
    final ArrayList<String> data = new ArrayList<>();
    @Override
    protected void onCreate() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        for (int i = 0; i < 50; i++) {
            data.add(""+i);
        }


        BaseQuickAdapter<String, BaseViewHolder> commonAdapter = new BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1, data) {
            @Override
            protected void convert(BaseViewHolder holder, String s) {
                holder.setText(android.R.id.text1, s);
            }
        };
        recyclerView.setAdapter(commonAdapter);
        findViewById(R.id.btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(0);
                commonAdapter.notifyDataSetChanged();
            }
        });

    }
}
