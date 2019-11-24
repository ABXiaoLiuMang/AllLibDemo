package com.dale.popup_demo.custom;

import android.content.Context;

import androidx.annotation.NonNull;

import com.dale.libdemo.R;
import com.lxj.xpopup.impl.FullScreenPopupView;

/**
 * Description:
 * Create by lxj, at 2019/3/12
 */
public class CustomFullScreenPopup extends FullScreenPopupView {
    public CustomFullScreenPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_fullscreen_popup;
    }
}
