package com.dale;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dale.fragment_demo.MainFragmentActivity;
import com.dale.framework.ui.ABBaseActivity;
import com.dale.kotlin.KotlinActivity;
import com.dale.libdemo.R;
import com.dale.view.SplashTextView;
import com.dale.worker_demo.ChartDemoActivity;
import com.dale.xutils.GlideUtil;

public class SplashActivity extends ABBaseActivity implements SplashTextView.onFinishListener {

    SplashTextView splashTextView;
    ImageView bg;
    private static final float SCALE_END = 1.13F;
    private static final int ANIMATION_TIME = 2000;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViewsAndEvents() {
        bg = findViewById(R.id.bg);
        splashTextView = findViewById(R.id.splashTextView);
        splashTextView.setOnFinishListener(this);
        startAnim();
        Glide.with(mContext)
                .load("http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg")
                .into(bg);
    }

    @Override
    public void onFinish() {
//       goActivity(MainFragmentActivity.class);
//       goActivity(GuideActivity.class);
       goActivity(KotlinActivity.class);
//       goActivity(DemoActivity.class);
       finish();
    }

    private void startAnim() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(bg, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(bg, "scaleY", 1f, SCALE_END);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_TIME).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {


            }
        });
    }


}
