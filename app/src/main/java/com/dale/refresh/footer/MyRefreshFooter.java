package com.dale.refresh.footer;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dale.libdemo.R;
import com.dale.utils.ResUtils;
import com.dale.utils.SizeUtils;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;


public class MyRefreshFooter extends FrameLayout implements RefreshFooter {
    private static final String mTextPulling = "查看更多";//"上拉加载更多";
    private static final String mTextLoading = "正在加载...";//"正在加载...";
    private static final String mTextNothing = "没有更多了～";//"没有更多数据了";

    private TextView tvContent;
    private boolean mNoMoreData = false;

    public MyRefreshFooter(@NonNull Context context) {
        super(context);
        initView();
    }

    public MyRefreshFooter(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyRefreshFooter(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        tvContent = new TextView(getContext());
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        tvContent.setLayoutParams(layoutParams);
        tvContent.setPadding(0, SizeUtils.dp2px(10),0, SizeUtils.dp2px(10));
        tvContent.setText(mTextPulling);
        tvContent.setTextSize(TypedValue.COMPLEX_UNIT_DIP,12);
        tvContent.setTextColor(ResUtils.getColor(R.color.black));
        addView(tvContent);
    }

    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        if (noMoreData){
            tvContent.setText(mTextNothing);
        }else {
            tvContent.setText(mTextPulling);
        }
        mNoMoreData = noMoreData;
        return false;
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        return 10;
    }


    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        if (!mNoMoreData) {
            switch (newState) {
                case Loading:
                case LoadReleased:
                    tvContent.setText(mTextLoading);
                    break;
                case LoadFinish:
                    tvContent.setText("");
                    break;
            }
        }else {
            tvContent.setText(mTextNothing);
        }
    }
}
