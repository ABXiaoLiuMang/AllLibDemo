package com.dale.net.callback;

import java.io.File;

/**
 * create by Dale
 * create on 2019/7/14
 * description: 文件下载回调
 */
public interface DownCallBack extends com.dale.net.callback.OnCallBack<File> {

     void onProgress(Integer integer);

}
