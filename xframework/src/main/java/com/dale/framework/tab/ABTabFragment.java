package com.dale.framework.tab;

import android.os.Bundle;

import com.dale.framework.ui.ABBaseFragment;

import me.yokeyword.fragmentation.ISupportFragment;

public abstract class ABTabFragment extends ABBaseFragment {

    /**
     * 启动新的Fragment，并能接收到新Fragment的数据返回
     * @param toClass
     */
    public void start(Class<? extends ISupportFragment> toClass) {
        ((ABBaseFragment)getParentFragment()).start(toClass);
    }

    public void start(Class<? extends ISupportFragment> toClass, Bundle bundle) {
        ((ABBaseFragment)getParentFragment()).start(toClass,bundle);
    }
}
