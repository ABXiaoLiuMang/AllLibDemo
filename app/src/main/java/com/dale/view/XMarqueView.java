package com.dale.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.dale.adapter.MarqueAdapter;
import com.dale.framework.util.TimerHelper;
import com.dale.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class XMarqueView extends RecyclerView implements LifecycleObserver {

    private              int           scrollCount = 0;
    private MarqueAdapter mMarqueAdapter;
    private static final int           speedScroll = 1500;
    private TimerHelper mTimerHelper;
    private              float         SPEED       = 15;// 表示15秒

    public XMarqueView(Context context) {
        super(context);
    }

    public XMarqueView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        initTimer();
    }

    public XMarqueView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, State state, int position) {
                LinearSmoothScroller smoothScroller = new LinearSmoothScroller(getContext()) {

                    /**
                     * 1000毫秒1像素
                     */
                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        return SPEED / ScreenUtils.getScreenWidth() * 1000;
                    }
                };
                smoothScroller.setTargetPosition(position);
                startSmoothScroll(smoothScroller);

            }
        };

        setLayoutManager(layoutManager);
        setHasFixedSize(true);
        setItemViewCacheSize(4);
        setDrawingCacheEnabled(true);
        setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        mMarqueAdapter = new MarqueAdapter();
        setAdapter(mMarqueAdapter);


        mMarqueAdapter.setOnItemClickListener((adapter, view, position) -> {

        });
    }

    private void initTimer() {
        mTimerHelper = new TimerHelper(new TimerTask() {
            @Override
            public void run() {
                if (getAdapter() != null && scrollCount == getAdapter().getItemCount()) {
                    mMarqueAdapter.reload();
                }
                smoothScrollToPosition((scrollCount++));

                //                handler.postDelayed(this, speedScroll);
            }
        }, speedScroll);
    }


    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter instanceof MarqueAdapter) {
            mMarqueAdapter = (MarqueAdapter) adapter;
        }
    }

    public void setList(List<String> list) {
        if (list == null) {
            return;
        }
        List<String> copyList = new ArrayList<>(list.size());
        copyList.addAll(list);
        mMarqueAdapter.setNewData(copyList);
    }

    public void resetData() {
        setList(mMarqueAdapter.getData());
    }

    public MarqueAdapter getMarqueAdapter() {
        return mMarqueAdapter;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return false;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        mTimerHelper.start(0);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        mTimerHelper.pause();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        if (((Activity) getContext()).isFinishing()) {
            mMarqueAdapter.getData().clear();
        }
    }


    //new auto scroll
    public void autoScroll(FragmentActivity activity) {
        scrollCount = 0;
        activity.getLifecycle().addObserver(this);
        mTimerHelper.life(activity);
        mTimerHelper.start(0);
        if (getAdapter() != null && getAdapter().getItemCount() > 0) {
            scrollToPosition(0);
        }
    }

    //new auto scroll
    public void autoScroll(Fragment fragment) {
        scrollCount = 0;
        mTimerHelper.life(fragment);
        mTimerHelper.start(0);
        if (getAdapter() != null && getAdapter().getItemCount() > 0) {
            scrollToPosition(0);
        }
    }


}

