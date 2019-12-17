package com.dale.framework_demo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.libdemo.R;
import com.dale.utils.LogUtils;

/**
 * create by Dale
 * create on 2019/7/27
 * description:
 */
public class PersonAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public PersonAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
//        helper.setText(R.id.tv_mudule,"postion:" + helper.getLayoutPosition());
        LogUtils.d("-"+ helper.getLayoutPosition() +"  -" + helper.getAdapterPosition() +"  ->" + helper.getOldPosition());
        helper.setText(R.id.tv_mudule,"postion:" + helper.getLayoutPosition() +"  name:" + item);
    }
}
