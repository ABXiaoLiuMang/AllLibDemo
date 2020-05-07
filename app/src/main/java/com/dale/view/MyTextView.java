package com.dale.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MyTextView extends TextView {

    String txt = "http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg";


    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        TextPaint tp = new TextPaint();
        tp.setColor(Color.BLUE);
        tp.setStyle(Paint.Style.FILL);
        tp.setTextSize(50);
        String message =txt;
        StaticLayout myStaticLayout = new StaticLayout(message, tp, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        myStaticLayout.draw(canvas);
//        canvas.restore();
    }

}
