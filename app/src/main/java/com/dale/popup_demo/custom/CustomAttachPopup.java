package com.dale.popup_demo.custom;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.dale.libdemo.R;
import com.dale.popup_demo.XPopupApp;
import com.dale.utils.ToastUtils;
import com.lxj.xpopup.core.HorizontalAttachPopupView;

/**
 * Description: 自定义Attach弹窗，水平方向的
 * Create by lxj, at 2019/3/13
 */
public class CustomAttachPopup extends HorizontalAttachPopupView {
    public CustomAttachPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_attach_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.tv_zan).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong("赞");
                dismiss();
            }
        });
        findViewById(R.id.tv_comment).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong("评论");
                dismiss();
            }
        });
    }

}
