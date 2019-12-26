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
import com.zbj.videoplayer.utils.VideoPlayerUtils;


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
        String url = ConstantVideo.VideoPlayerList[0];
        long savedPlayPosition = VideoPlayerUtils.getSavedPlayPosition(TestNextActivity.this, url);
        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.setDarkMode(this);//状态栏图标白色
        videoPlayer = (VideoPlayer) findViewById(R.id.nice_video_player);

        videoPlayer.setPlayerType(ConstantKeys.IjkPlayerType.TYPE_IJK);
        videoPlayer.setUp(url, null);
        VideoPlayerController controller = new VideoPlayerController(this);
        controller.setTitle("办公室小野开番外了，居然在办公室开澡堂！老板还点赞？");
        controller.setLength(98000);
        Glide.with(this)
                .load("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-30-43.jpg")
                .placeholder(R.drawable.image_default)
                .into(controller.imageView());
        //设置中间播放按钮是否显示
        controller.setTopPadding(24.0f);
        controller.setTopVisibility(false);
        videoPlayer.setController(controller);
        videoPlayer.continueFromLastPosition(true);
        videoPlayer.start(savedPlayPosition);


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
