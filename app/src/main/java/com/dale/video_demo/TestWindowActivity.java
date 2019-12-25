package com.dale.video_demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.dale.libdemo.R;
import com.zbj.videoplayer.manager.VideoPlayerManager;
import com.zbj.videoplayer.player.VideoPlayer;
import com.zbj.videoplayer.window.FloatPlayerView;
import com.zbj.videoplayer.window.FloatWindow;
import com.zbj.videoplayer.window.MoveType;
import com.zbj.videoplayer.window.WindowScreen;
import com.zbj.videoplayer.window.WindowUtil;


/**
 * @author yc
 */
public class TestWindowActivity extends BaseActivity implements View.OnClickListener {

    private VideoPlayer mVideoPlayer;
    private Button mBtn1;
    private Button mBtn2;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*
         * 这里在返回主页的时候销毁了，因为不想和DEMO中其他页面冲突
         */
        VideoPlayerManager.instance().releaseVideoPlayer();
        FloatWindow.destroy();
    }


    @Override
    public void onBackPressed() {
        if (VideoPlayerManager.instance().onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    public int getContentView() {
        return R.layout.activity_test_window;
    }

    @Override
    public void initView() {
        mVideoPlayer = (VideoPlayer) findViewById(R.id.video_player);
        mBtn1 = (Button) findViewById(R.id.btn_1);
        mBtn1.setOnClickListener(this);
        mBtn2 = (Button) findViewById(R.id.btn_2);
        mBtn2.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= 23) {
            if (!WindowUtil.hasPermission(this)) {
                requestAlertWindowPermission();
            }
        }
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                startWindow();
                break;
            case R.id.btn_2:
                startActivity(new Intent(this, TestFullActivity.class));
                break;
            default:
                break;
        }
    }

    private void startWindow() {
        if (FloatWindow.get() != null) {
            return;
        }
        String url = "http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4";
        FloatPlayerView.setUrl(url);
        FloatPlayerView floatPlayerView = new FloatPlayerView(getApplicationContext());
        floatPlayerView.setCompletedListener(new FloatPlayerView.CompletedListener() {
            @Override
            public void Completed() {
                FloatWindow.get().hide();
            }
        });
        FloatWindow
                .with(getApplicationContext())
                .setView(floatPlayerView)
                //.setWidth(WindowScreen.WIDTH, 0.4f)
                //.setHeight(WindowScreen.WIDTH, 0.3f)
                //这个是设置位置
                .setX(WindowScreen.width, 0.8f)
                .setY(WindowScreen.height, 0.3f)
                .setMoveType(MoveType.slide)
                .setFilter(false)
                //.setFilter(true, WindowActivity.class, EmptyActivity.class)
                .setMoveStyle(500, new BounceInterpolator())
                .build();
        FloatWindow.get().show();
    }


    @RequiresApi(api = 23)
    private void requestAlertWindowPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, 1);
    }

    @RequiresApi(api = 23)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Build.VERSION.SDK_INT >= 23) {
            if (WindowUtil.hasPermission(this)) {

            } else {
                this.finish();
            }
        }
    }


}
