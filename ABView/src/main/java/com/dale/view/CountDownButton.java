package com.dale.view;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;


/**
 * descibe 短信验证码倒计时button
 */
public class CountDownButton extends AppCompatTextView {

    private long time = 60 * 1000;

    private String beforeText = "获取验证码";

    private String afterText = "重新获取";

    private CountDownTimer timer;


    public CountDownButton(Context context) {
        super(context);
        initView();
    }

    public CountDownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CountDownButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    /**
     * 初始化操作
     */
    private void initView() {
        if (!TextUtils.isEmpty(getText())) {
            beforeText = getText().toString().trim();
        }
        setText(beforeText);
        initTimer();
    }

    /**
     * 开始倒计时
     */
    public void start() {
        timer.start();
        setText(time / 1000 + "S后重试");
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
                    setEnabled(false);
                    setText(millisUntilFinished / 1000 + "秒后重发");
                }

                @Override
                public void onFinish() {
                    setEnabled(true);
                    setText(afterText);
                }
            };
        }
    }

}
