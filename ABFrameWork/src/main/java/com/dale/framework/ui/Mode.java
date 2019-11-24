package com.dale.framework.ui;

public enum Mode {

    DISABLED,

    PULL_FROM_START,

    PULL_FROM_END,

    BOTH;

    static Mode getDefault() {
        return BOTH;
    }
}
