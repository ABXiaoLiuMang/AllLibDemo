package com.dale.xweb.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.just.agentweb.BaseIndicatorView;


public class XLoading extends BaseIndicatorView {
    public XLoading(Context context) {
        super(context);
    }

    public XLoading(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XLoading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public FrameLayout.LayoutParams offerLayoutParams() {
        return new FrameLayout.LayoutParams(-1, -1);
    }
}

