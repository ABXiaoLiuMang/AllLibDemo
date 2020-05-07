package com.dale.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.core.view.MotionEventCompat;
import androidx.customview.widget.ViewDragHelper;

import com.dale.libdemo.R;
import com.dale.utils.SizeUtils;


public class DragShareView extends RelativeLayout {

    ViewDragHelper dragHelper;
    View contentView;
    View codeImageView;
    View codeTextView;
    View tipTextView;

    public DragShareView(Context context) {
        this(context, null);
    }


    public DragShareView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = findViewById(R.id.contentView);
        codeImageView = findViewById(R.id.codeImageView);
        codeTextView = findViewById(R.id.codeTextView);
        tipTextView = findViewById(R.id.tipTextView);
        if (contentView == null) {
            throw new RuntimeException("内容布局ID必须为contentView");
        }
        if (codeImageView == null) {
            throw new RuntimeException("可拖拽二维码ID必须为codeImageView");
        }
        if (codeTextView == null) {
            throw new RuntimeException("可拖拽二维码text ID必须为codeTextView");
        }
        if (tipTextView == null) {
            throw new RuntimeException("可拖拽tips ID必须为tipTextView");
        }
    }

    private void init() {
        dragHelper = ViewDragHelper.create(DragShareView.this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == tipTextView || child == codeTextView || child == codeImageView ;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                if (top > getHeight() - child.getMeasuredHeight()) {
                    top = getHeight() - child.getMeasuredHeight();
                } else if (top < 0) {
                    top = 0;
                }
                return top;
            }


            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if (left > getWidth() - child.getMeasuredWidth()) {
                    left = getWidth() - child.getMeasuredWidth();
                } else if (left < 0) {
                    left = 0;
                }
                return left;
            }


            @Override
            public int getViewVerticalDragRange(View child) {
                return getMeasuredHeight() - child.getMeasuredHeight();
            }


            @Override
            public int getViewHorizontalDragRange(View child) {
                return getMeasuredWidth() - child.getMeasuredWidth();
            }


            //  拖拽的 view 位置发生变化时被回调
            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
            }


            @Override
            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);
            }


            // 在释放拖拽的 view 时，会回调
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if (releasedChild == codeTextView) {
                    float x = codeTextView.getX();
                    float y = codeTextView.getY();
                 RelativeLayout.LayoutParams layoutParams = (LayoutParams) codeTextView.getLayoutParams();
                 layoutParams.leftMargin = (int) x;
                 layoutParams.topMargin = (int) y;
                 codeTextView.setLayoutParams(layoutParams);






                    Log.d("Dream","x:" + x +"  y:" + y);

//                    savePosition();
                    // 移动到指定位置
//                    dragHelper.smoothSlideViewTo(releasedChild, (int) x, (int) y);
//                    invalidate();

//                    codeTextView.layout((int) x, (int) y,
//                            (int) x + codeTextView.getMeasuredWidth(), (int) y + codeTextView.getMeasuredHeight());
                }
            }
        });

    }



    @Override

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            dragHelper.cancel();
            return false;
        }
        return dragHelper.shouldInterceptTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }


    @Override
    public void computeScroll() {
        if (dragHelper.continueSettling(true)) {
            invalidate();
        }
    }


    /**
     * 刷新二维码位置
     */
    public void refreshImageLayout() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) codeImageView.getLayoutParams();
        layoutParams.setMargins(SizeUtils.dp2px(15), SizeUtils.dp2px(15), SizeUtils.dp2px(15), SizeUtils.dp2px(15));
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        codeImageView.setLayoutParams(layoutParams);
    }


}
