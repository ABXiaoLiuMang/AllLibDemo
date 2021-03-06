/*
Copyright 2017 yangchong211（github.com/yangchong211）

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.zbj.videoplayer.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zbj.videoplayer.constant.ConstantKeys;
import com.zbj.videoplayer.controller.AbsVideoPlayerController;
import com.zbj.videoplayer.manager.VideoPlayerManager;
import com.zbj.videoplayer.player.VideoPlayer;
import com.zbj.videoplayer.utils.NetworkUtils;
import com.zbj.videoplayer.utils.VideoLogUtil;


/**
 * <pre>
 *     desc  : 网络状态监听广播
 *     revise:
 * </pre>
 */
public class NetChangedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        VideoLogUtil.i("网络状态监听广播接收到数据了");
        VideoPlayer mVideoPlayer = VideoPlayerManager.instance().getCurrentVideoPlayer();
        if (mVideoPlayer!=null){
            AbsVideoPlayerController controller = mVideoPlayer.getController();
            switch (NetworkUtils.getConnectState(context)) {
                case MOBILE:
                    VideoLogUtil.i("当网络状态监听前连接了移动数据");
                    break;
                case WIFI:
                    VideoLogUtil.i("网络状态监听当前连接了Wifi");
                    break;
                case UN_CONNECTED:
                    VideoLogUtil.i("网络状态监听当前没有网络连接");
                    if (mVideoPlayer.isPlaying() || mVideoPlayer.isBufferingPlaying()) {
                        VideoLogUtil.i("网络状态监听当前没有网络连接---设置暂停播放");
                        mVideoPlayer.pause();
                    }
                    if (controller!=null){
                        controller.onPlayStateChanged(ConstantKeys.CurrentState.STATE_ERROR);
                    }
                    break;
                default:
                    VideoLogUtil.i("网络状态监听其他情况");
                    break;
            }
        }
    }
}
