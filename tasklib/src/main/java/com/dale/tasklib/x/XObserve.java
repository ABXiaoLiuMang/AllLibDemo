package com.dale.tasklib.x;

public class XObserve<T> {
    Emitter<T> emitter;
    public static  <T> XObserve<T> create(Emitter<T> emitter) {
        XObserve<T> xObserve = new XObserve<>();
        xObserve.emitter = emitter;
        return xObserve;
    }
}
