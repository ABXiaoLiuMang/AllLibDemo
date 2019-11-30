package com.dale.framework.util;

import android.view.View;

import androidx.core.widget.NestedScrollView;

/**
 * create by Dale
 * create on 2019/8/19
 * description: 标题滚动透明变化帮助类
 */
public class ScrollChangeHelper implements NestedScrollView.OnScrollChangeListener {

    private int lastScrollY = 0;
    private int mScrollY = 0;
    private int scrollHeight;
    private View alphaView;
    private onMoveRatioListener onMoveRatioListener;

    public ScrollChangeHelper(Builder builder) {
        init(builder);
    }

    void init(Builder builder) {
        scrollHeight = builder.scrollHeight;
        alphaView = builder.alphaView;
        onMoveRatioListener = builder.onMoveRatioListener;
        builder.scrollView.setOnScrollChangeListener(this);
        alphaView.setAlpha(0);
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (lastScrollY < scrollHeight) {
            scrollY = Math.min(scrollHeight, scrollY);
            mScrollY = scrollY > scrollHeight ? scrollHeight : scrollY;
            float ratio = 1f * mScrollY / scrollHeight;
            if(onMoveRatioListener != null){
                onMoveRatioListener.onMoveRatio(ratio);
            }else {
                alphaView.setAlpha(ratio);
            }
        }
        lastScrollY = scrollY;
    }


    public interface onMoveRatioListener{
         void onMoveRatio(float ratio);
    }

    public static class Builder {
        private int scrollHeight;
        private NestedScrollView scrollView;
        private View alphaView;
        private onMoveRatioListener onMoveRatioListener;

        public Builder scrollHeight(int scrollHeight) {
            this.scrollHeight = scrollHeight;
            return this;
        }

        public Builder setNestedScrollView(NestedScrollView scrollView) {
            this.scrollView = scrollView;
            return this;
        }

        public Builder setAlphaView(View alphaView) {
            this.alphaView = alphaView;
            return this;
        }

        public Builder setOnMoveRatioListener(onMoveRatioListener onMoveRatioListener) {
            this.onMoveRatioListener = onMoveRatioListener;
            return this;
        }

        public ScrollChangeHelper build() {
            return new ScrollChangeHelper(this);
        }

    }
}
