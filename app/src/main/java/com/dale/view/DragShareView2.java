package com.dale.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import androidx.core.view.MotionEventCompat;
import androidx.customview.widget.ViewDragHelper;

import com.dale.libdemo.R;
import com.dale.log.LogUtils;


public class DragShareView2 extends RelativeLayout {

    ViewDragHelper dragHelper;
    View contentView;
    View codeImageView;
    View codeTextView;
    View tipTextView;

    public int mScrollX;
    public VelocityTracker mVelocityTracker;
    public Scroller mScroller;
    public int mDw;
    public float mX;


    public DragShareView2(Context context) {
        this(context, null);
    }


    public DragShareView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
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
        dragHelper = ViewDragHelper.create(DragShareView2.this, 1.0f, new ViewDragHelper.Callback() {
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
            }
        });

    }

    int mLastX;
    int mLastY;
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        if(isTouchPointInView(codeImageView,x,y) || isTouchPointInView(codeTextView,x,y) || isTouchPointInView(tipTextView,x,y)){
            int xx = (int) event.getX();
            int yy = (int) event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    ViewGroup view = (ViewGroup) getParent();
                    view.requestDisallowInterceptTouchEvent(false);
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    int deltaX = xx - mLastX;
                    int deltaY = yy - mLastY;
                    if (Math.abs(deltaX) > Math.abs(deltaY)) {
                        ViewGroup view = (ViewGroup) getParent();
                        view.requestDisallowInterceptTouchEvent(true);
                    }
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    break;
                }
                default:
                    break;
            }

            mLastX = x;
            mLastY = y;
        }


        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int x = (int) ev.getRawX();
        int y = (int) ev.getRawY();
        if(isTouchPointInView(codeImageView,x,y) || isTouchPointInView(codeTextView,x,y) || isTouchPointInView(tipTextView,x,y)){
            Log.d("Dream","1在");
            int action = MotionEventCompat.getActionMasked(ev);
            if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
                dragHelper.cancel();
                return false;
            }
            return dragHelper.shouldInterceptTouchEvent(ev);

        }else {
            Log.d("Dream","1不在");

            return super.onInterceptTouchEvent(ev);
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        if(isTouchPointInView(codeImageView,x,y) || isTouchPointInView(codeTextView,x,y) || isTouchPointInView(tipTextView,x,y)){
            Log.d("Dream","2在");
            dragHelper.processTouchEvent(event);
        }else {
            Log.d("Dream","2不在");
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    View parent = (View) getParent();
                    mDw = getWidth() - parent.getWidth();
                    if (mDw > 0) {
                        mScroller.forceFinished(true);
                        if (mVelocityTracker == null) {
                            mVelocityTracker = VelocityTracker.obtain();
                            mVelocityTracker.addMovement(event);
                        }
                        mScrollX = getScrollX();
                        mX = event.getX();
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mDw > 0) {

                        mVelocityTracker.addMovement(event);

                        float xx = event.getX();
                        int dx = (int) (mX - xx + mScrollX);
                        if (dx < 0) {
                            dx = 0;
                        }
                        if (dx > mDw) {
                            dx = mDw;
                        }
                        scrollTo(dx, 0);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (mDw > 0) {
                        mVelocityTracker.addMovement(event);
                        mVelocityTracker.computeCurrentVelocity(1000);
                        int velocityX = (int) mVelocityTracker.getXVelocity();
                        mScroller.fling(getScrollX(), 0, -velocityX, 0, 0, mDw, 0, 0);
                        postInvalidate();
//                        if (mVelocityTracker != null) {
//                            mVelocityTracker.recycle();
//                            mVelocityTracker = null;
//                        }
                    }
                    break;
            }
        }


        return true;
    }


    @Override
    public void computeScroll() {
        if (dragHelper.continueSettling(true)) {
            invalidate();
        }
        //*****************

        // 如果返回true，表示动画还没有结束
        // 因为前面startScroll，所以只有在startScroll完成时 才会为false
        if (mScroller.computeScrollOffset()) {
            // 产生了动画效果 每次滚动一点
            scrollTo(mScroller.getCurrX(), 0);
            //刷新View 否则效果可能有误差
            postInvalidate();
        }
    }

    public boolean isTouchPointInView(View view, int x, int y) {
        if (view == null) {
            return false;
        }
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        if (y >= top && y <= bottom && x >= left
                && x <= right) {
            return true;
        }
        return false;
    }

}
