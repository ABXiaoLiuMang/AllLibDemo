package com.dale.net.callback;


import com.dale.net.bean.ProgressModel;

/**
 * 上传进度监听
 */
public interface ProgressListener {
    void progress(ProgressModel progressModel);
}
