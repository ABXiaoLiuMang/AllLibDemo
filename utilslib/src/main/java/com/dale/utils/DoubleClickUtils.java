package com.dale.utils;

public class DoubleClickUtils {

    private static long lastClickTime;
    private final static int SPACE_TIME_SHORT = 500;

    public synchronized static boolean isFastClick() {
        return isDoubleClickInner(SPACE_TIME_SHORT);
    }


    public synchronized static boolean isDoubleClickInner(int spaceTime) {
        long currentTime = System.currentTimeMillis();
        boolean isClick2;
        if (currentTime - lastClickTime > spaceTime || lastClickTime == 0) {
            isClick2 = false;
            lastClickTime = currentTime;
        } else {
            isClick2 = true;
        }
        return isClick2;
    }
}
