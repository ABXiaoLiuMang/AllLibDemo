package com.dale.livedatademo.ui;


import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;


public class SingleLiveData<T> extends MutableLiveData<T> {

    private final AtomicBoolean mPending = new AtomicBoolean(false);

    private final boolean single;

    public SingleLiveData(boolean single) {
        this.single = single;
    }

    @MainThread
    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        if (single) {
            super.observe(owner, t -> {
                if (mPending.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            });
        } else {
            super.observe(owner, observer);
        }
    }

    @MainThread
    @Override
    public void setValue(@Nullable T t) {
        if (single) {
            mPending.set(true);
        }
        super.setValue(t);
    }

    @MainThread
    public void call() {
        setValue(null);
    }
}
