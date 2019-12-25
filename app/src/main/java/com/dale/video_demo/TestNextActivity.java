package com.dale.video_demo;

import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.dale.libdemo.R;
import com.dale.utils.StatusBarUtil;
import com.zbj.videoplayer.constant.ConstantKeys;
import com.zbj.videoplayer.controller.AbsVideoPlayerController;
import com.zbj.videoplayer.controller.VideoPlayerController;
import com.zbj.videoplayer.inter.listener.OnVideoControlListener;
import com.zbj.videoplayer.manager.VideoPlayerManager;
import com.zbj.videoplayer.player.VideoPlayer;


/**
 * @author yc
 */
public class TestNextActivity extends BaseActivity implements View.OnClickListener {

    private VideoPlayer videoPlayer;


    @Override
    public int getContentView() {
        return R.layout.activity_testnext_video;
    }

    @Override
    public void initView() {
        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.setDarkMode(this);//状态栏图标白色
        videoPlayer = (VideoPlayer) findViewById(R.id.nice_video_player);
        AbsVideoPlayerController controller = VideoPlayerManager.instance().getCurrentVideoPlayer().getController();
        VideoPlayerManager.instance().setCurrentVideoPlayer(videoPlayer);
        videoPlayer.setController(controller);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
    }

}
