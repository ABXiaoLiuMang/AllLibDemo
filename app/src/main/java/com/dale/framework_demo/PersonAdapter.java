package com.dale.framework_demo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * create by Dale
 * create on 2019/7/27
 * description:
 */
public class PersonAdapter extends BaseQuickAdapter<Person, BaseViewHolder> {
    public PersonAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Person item) {

    }
}
