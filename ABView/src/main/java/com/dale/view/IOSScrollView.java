package com.dale.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * 弹性view
 */
public class IOSScrollView extends ScrollView {

    private static final int size = 2;
    private View inner;
    private float y;
    private Rect normal = new Rect();

    public IOSScrollView(Context context) {
        super(context);
    }

    public IOSScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IOSScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            inner = getChildAt(0);
        }
        super.onFinishInflate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (inner == null) {
            return super.onTouchEvent(ev);
        } else {
            commOnTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    public void commOnTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                y = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                if (isNeedAnimation()) {
                    animation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                final float preY = y;
                float nowY = ev.getY();
                int deltaY = (int) (preY - nowY) / size;

                y = nowY;
                if (isNeedMove()) {
                    if (normal.isEmpty()) {
                        normal.set(inner.getLeft(), inner.getTop(), inner.getRight(), inner.getBottom());
                        return;
                    }
                    int yy = inner.getTop() - deltaY;

                    inner.layout(inner.getLeft(), yy, inner.getRight(), inner.getBottom() - deltaY);
                }
                break;
            default:
                break;
        }
    }

    public void animation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, inner.getTop(), normal.top);
        translateAnimation.setDuration(500);
        inner.startAnimation(translateAnimation);
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);
        normal.setEmpty();
    }

    public boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    public boolean isNeedMove() {
        int offset = inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        if (scrollY == 0 || scrollY == offset) {
            return true;
        }
        return false;
    }

}
