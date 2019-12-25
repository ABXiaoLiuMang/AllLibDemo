package com.dale.video_demo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.dale.libdemo.R;
import com.dale.utils.StatusBarUtil;
import com.zbj.videoplayer.constant.ConstantKeys;
import com.zbj.videoplayer.controller.VideoPlayerController;
import com.zbj.videoplayer.inter.listener.OnVideoControlListener;
import com.zbj.videoplayer.manager.VideoPlayerManager;
import com.zbj.videoplayer.player.VideoPlayer;


/**
 * @author yc
 */
public class TestFullActivity extends BaseActivity implements View.OnClickListener {

    private VideoPlayer videoPlayer;
    private Button mBtnTiny1;
    private Button mBtnTiny2;
    private Button mBtnTiny3;

    @Override
    protected void onStop() {
        super.onStop();
        VideoPlayerManager.instance().suspendVideoPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VideoPlayerManager.instance().releaseVideoPlayer();
    }

    @Override
    public void onBackPressed() {
        if (VideoPlayerManager.instance().onBackPressed()) {
            return;
        } else {
            VideoPlayerManager.instance().releaseVideoPlayer();
        }
        super.onBackPressed();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        VideoPlayerManager.instance().resumeVideoPlayer();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_test_video2;
    }

    @Override
    public void initView() {
        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.setDarkMode(this);//状态栏图标白色
        videoPlayer = (VideoPlayer) findViewById(R.id.nice_video_player);
        mBtnTiny1 = (Button) findViewById(R.id.btn_tiny_1);
        mBtnTiny2 = (Button) findViewById(R.id.btn_tiny_2);
        mBtnTiny3 = (Button) findViewById(R.id.btn_tiny_3);

        videoPlayer.setPlayerType(ConstantKeys.IjkPlayerType.TYPE_IJK);
        videoPlayer.setUp(ConstantVideo.VideoPlayerList[0], null);
        VideoPlayerController controller = new VideoPlayerController(this);
        controller.setTitle("办公室小野开番外了，居然在办公室开澡堂！老板还点赞？");
        controller.setLength(98000);
        Glide.with(this)
                .load("http://tanzi27niu.cdsb.mobi/wps/wp-content/uploads/2017/05/2017-05-17_17-30-43.jpg")
                .placeholder(R.drawable.image_default)
                .into(controller.imageView());
        //设置中间播放按钮是否显示
        controller.setTopPadding(24.0f);
        controller.setTopVisibility(true);
        controller.setOnVideoControlListener(new OnVideoControlListener() {
            @Override
            public void onVideoControlClick(int type) {
                switch (type){
                    case ConstantKeys.VideoControl.DOWNLOAD:
                        //BaseToast.showRoundRectToast("下载");
                        break;
                    case ConstantKeys.VideoControl.SHARE:
                        //BaseToast.showRoundRectToast("分享");
                        break;
                    case ConstantKeys.VideoControl.MENU:
                        //BaseToast.showRoundRectToast("更多");
                        break;
                    case ConstantKeys.VideoControl.AUDIO:
                        //BaseToast.showRoundRectToast("下载");
                        break;
                    default:
                        break;
                }
            }
        });
        videoPlayer.setController(controller);
        videoPlayer.continueFromLastPosition(false);
        videoPlayer.postDelayed(new Runnable() {
            @Override
            public void run() {
                videoPlayer.start();
            }
        },500);
    }

    @Override
    public void initListener() {
        mBtnTiny1.setOnClickListener(this);
        mBtnTiny2.setOnClickListener(this);
        mBtnTiny3.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tiny_1:
                if (videoPlayer.isIdle()) {
                    videoPlayer.start();
                }
                videoPlayer.enterVerticalScreenScreen();
                break;
            case R.id.btn_tiny_2:
                if (videoPlayer.isIdle()) {
                    videoPlayer.start();
                }
                videoPlayer.enterFullScreen();
                break;
            case R.id.btn_tiny_3:
                VideoPlayerManager.instance().suspendVideoPlayer();
                startActivity(TestNextActivity.class);
                break;
            default:
                break;
        }
    }

}
