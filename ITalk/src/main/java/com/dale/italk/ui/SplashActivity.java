package com.dale.italk.ui;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dale.framework.ui.ABBaseActivity;
import com.dale.italk.R;
import com.dale.net.callback.NetObserver;
import com.dale.net.exception.ErrorMessage;
import com.dale.talk.IMManager;
import com.dale.talk.entity.BaseEntity;
import com.dale.talk.entity.LoginResult;
import com.dale.view.SplashTextView;

public class SplashActivity extends ABBaseActivity implements SplashTextView.onFinishListener {

    SplashTextView splashTextView;
    ImageView bg;
    private static final float SCALE_END = 1.13F;
    private static final int ANIMATION_TIME = 2000;
    boolean isLogin = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void createProvider() {

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

//        IMManager.getInstance().loginResult.observe(this,new NetObserver<String>(){
//            @Override
//            protected void onSuccess(String s) {
//                isLogin = true;
//            }
//
//            @Override
//            protected void onError(ErrorMessage errorMessage) {
//
//            }
//        });
//
//        IMManager.getInstance().tokenLiveData.observe(this,new NetObserver<BaseEntity<LoginResult>>(){
//
//            @Override
//            protected void onSuccess(BaseEntity<LoginResult> baseEntity) {
//                IMManager.getInstance().connectIM(baseEntity.getResult().getToken(), false);
//            }
//
//            @Override
//            protected void onError(ErrorMessage errorMessage) {
//            }
//        });
    }

    @Override
    public void onFinish() {
        if(isLogin){
            goActivity(MainActivity.class);
        }else {
            goActivity(LoginActivity.class);
        }
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
