package com.dale.tasklib.x;

import androidx.annotation.NonNull;

public interface Emitter<T> {
    void onNext(@NonNull T value);
}
