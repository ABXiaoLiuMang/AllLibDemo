package com.dale.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.dale.utils.StringUtils;

/**
 * 闪屏倒计时控件
 */
public class SplashTextView extends AppCompatTextView implements View.OnClickListener {

    private long time;
    private onFinishListener onFinishListener;
    private CountDownTimer timer;
    private String millisText = "%d 跳过";


    public SplashTextView(Context context) {
        super(context);
        init();
    }

    public SplashTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SplashTextView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.SplashTextView, defStyle, 0);
        time = (long) a.getInteger(R.styleable.SplashTextView_millisText, 5 * 1000);
        init();
    }


    protected void init() {
        setGravity(Gravity.CENTER);
        setOnClickListener(this);
        setText(StringUtils.getFormatString(millisText, (time / 1000) + 1));
        initTimer();
        timer.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        clearTimer();
        super.onDetachedFromWindow();
    }

    private void initTimer() {
        if (timer == null) {
            timer = new CountDownTimer(time, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    setText(StringUtils.getFormatString(millisText, (millisUntilFinished / 1000) + 1));
                }

                @Override
                public void onFinish() {
                    if (onFinishListener != null) {
                        onFinishListener.onFinish();
                    }
                    setText(StringUtils.getFormatString(millisText, 0));
                }
            };
        }
    }

    /**
     * 清除倒计时
     */
    private void clearTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void setOnFinishListener(SplashTextView.onFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

    @Override
    public void onClick(View v) {
        if (onFinishListener != null) {
            onFinishListener.onFinish();
        }
    }

    public interface onFinishListener {
        void onFinish();
    }


}

