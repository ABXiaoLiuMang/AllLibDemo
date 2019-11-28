package com.dale.refresh.header;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.airbnb.lottie.LottieAnimationView;
import com.dale.libdemo.R;
import com.dale.utils.LogUtils;
import com.dale.utils.ResUtils;
import com.dale.utils.SizeUtils;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * desc:
 *
 * @author Jeff created on 2019/9/17.
 */
public class MyRefreshHeader extends LinearLayout implements RefreshHeader {
    private static final String REFRESH_SUCCESS = "刷新成功～";
    private static final String REFRESH_FAILS = "刷新失败～";
    private LottieAnimationView lottieAnimationView;
    private TextView mTitle;
    private boolean isShow;
    private boolean isFinish = false;
    private AlphaAnimation alphaAnimation;
    private ValueAnimator endAnimator;
    private boolean success;
    private String errorMessage;
    public MyRefreshHeader(Context context) {
        super(context);
        initView();
    }

    public MyRefreshHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyRefreshHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        lottieAnimationView = new LottieAnimationView(getContext());
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, SizeUtils.dp2px(50));
        lottieAnimationView.setLayoutParams(layoutParams);
        addView(lottieAnimationView);
        lottieAnimationView.setImageAssetsFolder("lottie/header/images/");

        mTitle = new TextView(getContext());
        LayoutParams titleLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTitle.setLayoutParams(titleLayoutParams);
        mTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP,12);
        mTitle.setTextColor(ResUtils.getColor(R.color.black));
        mTitle.setPadding(0, SizeUtils.dp2px(10),0,SizeUtils.dp2px(10));
        mTitle.setVisibility(GONE);
        addView(mTitle);

        endAnimator = ValueAnimator.ofFloat(0f, 1f);
        endAnimator.setDuration(300);
        endAnimator.addUpdateListener(animation -> {
            lottieAnimationView.setProgress((float) animation.getAnimatedValue());
        });

        alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setDuration(600);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setFillEnabled(true);
        alphaAnimation.setFillBefore(true);


        //添加依附的监听
        addOnAttachStateChangeListener(new OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {

            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                //lottieAnimationView.removeAllAnimatorListeners();

                endAnimator.removeAllUpdateListeners();
            }
        });
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
        mTitle.setText("");
        isFinish = false;
        if (lottieAnimationView.isAnimating()) return;
        if (percent >= 0.5 && !isShow){
            isShow = true;
            startAnim();
        }else if (percent < 0.5 && isShow){
            isShow = false;
           closeAnim();
        }
    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        lottieAnimationView.setRepeatCount(ValueAnimator.INFINITE);
        lottieAnimationView.setAnimation("lottie/header/header_loading.json");
        lottieAnimationView.playAnimation();
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        isFinish = true;
//        this.errorMessage = message;
        finishAnim(success);
        return 600;
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
        if (newState == RefreshState.PullDownCanceled ){
            lottieAnimationView.cancelAnimation();
        }
    }

    private void startAnim(){
        lottieAnimationView.setVisibility(VISIBLE);
        lottieAnimationView.setRepeatCount(0);
        lottieAnimationView.setAnimation("lottie/header/header_start.json");
        lottieAnimationView.playAnimation();
    }

    private void closeAnim(){
        lottieAnimationView.setRepeatCount(0);
        lottieAnimationView.setAnimation("lottie/header/header_end.json");
        lottieAnimationView.playAnimation();
    }

    private void finishAnim(boolean success){
        lottieAnimationView.setRepeatCount(0);
        lottieAnimationView.setAnimation("lottie/header/header_end.json");
        this.success = success;
        endAnimator.addListener(endListener);
        endAnimator.start();
    }

    private Animator.AnimatorListener endListener = new Animator.AnimatorListener() {

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            LogUtils.d("动画结束");
            lottieAnimationView.removeAnimatorListener(this);
            if (isFinish){
                isFinish = false;
                showFinishText();
                lottieAnimationView.cancelAnimation();
                lottieAnimationView.setVisibility(GONE);
            }
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    private void showFinishText(){
        if (success){
            mTitle.setText(REFRESH_SUCCESS);
        }else if (!TextUtils.isEmpty(errorMessage)){
            mTitle.setText(REFRESH_FAILS);
        }else {
            mTitle.setText(REFRESH_FAILS);
        }
        mTitle.setVisibility(VISIBLE);
        mTitle.setAnimation(alphaAnimation);
    }

}
