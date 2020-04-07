package com.dale;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.framework_demo.Person;
import com.dale.libdemo.R;

/**
 * create by Dale
 * create on 2019/7/27
 * description:
 */
public class DemoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public DemoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_mudule,item);
        TextView textView = helper.getView(R.id.tv_mudule);
        textView.setTextColor(0XFF4080FF);
    }
}
