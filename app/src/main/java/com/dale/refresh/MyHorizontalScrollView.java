//package com.dale.refresh;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.widget.HorizontalScrollView;
//
//public class MyHorizontalScrollView extends HorizontalScrollView {
//    public MyHorizontalScrollView(Context context) {
//        super(context);
//    }
//
//    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    private boolean mCanScroll = true;
//
//    private float mDownX;
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            mDownX = ev.getX();
//        }
//
//        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//            int scrollx = getScrollX();
//            if ((scrollx == 0 && mDownX - ev.getX() <= -10)
//                    || (getChildAt(0).getMeasuredWidth() <= (scrollx + mParentWhidth) && mDownX
//                    - ev.getX() >= 10)) {
//                mCanScroll = false;
//            }
//
//        }
//
//        if (ev.getAction() == MotionEvent.ACTION_UP
//                || ev.getAction() == MotionEvent.ACTION_CANCEL) {
//            mCanScroll = true;
//        }
//
//        if (this.mCanScroll) {
//            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
//            getParent().requestDisallowInterceptTouchEvent(true);
//            return super.onTouchEvent(ev);
//        } else {
//            getParent().requestDisallowInterceptTouchEvent(false);
//            return false;
//        }
//    }
//
//}
