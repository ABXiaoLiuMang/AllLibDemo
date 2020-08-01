
package com.dale.livebus.liveevent;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;

public class MainThreadManager {

    private static class SingletonHolder {
        private static final MainThreadManager INSTANCE = new MainThreadManager();
    }

    public static MainThreadManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private final Object mLock = new Object();

    private MainThreadManager() {
    }

    @Nullable
    private volatile Handler mMainHandler;

    public void postToMainThread(Runnable runnable) {
        if (mMainHandler == null) {
            synchronized (mLock) {
                if (mMainHandler == null) {
                    mMainHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        mMainHandler.post(runnable);
    }

    public boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
