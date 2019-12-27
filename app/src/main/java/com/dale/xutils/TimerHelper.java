package com.dale.xutils;

import android.os.Handler;
import android.os.Looper;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.TimerTask;

/**
 * Desc:自带生命周期,和fragment隐藏和显示的自动停止统计和开始统计
 **/
public class TimerHelper implements LifecycleObserver {
    private static final int STARTING = 1;
    private static final int PAUSE = 2;
    private static final int STOP = 3;
    private Handler handler;
    private TimerTask timerTask;
    private long delayMillis;//间隔执行时间
    /**
     * 循环次数
     */
    private long count;
    /**
     * 当前循环次数
     */
    private long currCount;
    private int timeStatus = -1;
    public TimerHelper(TimerTask timerTask, long delayMillis) {
        this.timerTask = timerTask;
        this.delayMillis = delayMillis;
    }

    public TimerHelper count(int count){
        this.count = count;
        return this;
    }


    public TimerHelper life(FragmentActivity activity){
        activity.getLifecycle().addObserver(this);
        return this;
    }

    /**
     * desc: 开始
     */
    public TimerHelper life(Fragment fragment){
        fragment.getLifecycle().addObserver(this);
        return this;
    }

    /**
     * @param firstDelay 第一次执行的时间
     */
    public void start(long firstDelay){
        timeStatus = STARTING;
        currCount = 0;
        if (handler == null){
            handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(task,firstDelay);
        }
    }

    /**
     * 开始
     */
    public void start(){
        start(delayMillis);
    }

    public void pause(){
        try{
            timeStatus = PAUSE;
            if (handler != null){
                handler.removeCallbacks(task);
                handler = null;
            }
        }catch (Exception e){
        }
    }

    private Runnable task = new Runnable() {
        @Override
        public void run() {
            currCount++;
            if (isContinue()){
                handler.postDelayed(task,delayMillis);
            } else{
                stop();
            }


            if (timerTask != null){
                timerTask.run();
            }
        }
    };

    private boolean isContinue(){
        return count <= 0 || currCount < count;
    }




    public boolean isRunning(){
        return handler != null;
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void stop(){
        try{
            timeStatus = STOP;
            if (handler != null){
                handler.removeCallbacks(task);
                handler = null;
            }
        }catch (Exception e){
        }
    }

}
