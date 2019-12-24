package com.dale.video_demo;

import android.graphics.Rect;

import androidx.recyclerview.widget.RecyclerView;

import com.dale.utils.WeakHandler;
import com.zbj.videoplayer.player.VideoPlayer;

/**
 * 计算滑动，自动播放的帮助类
 */
public class ScrollCalculatorHelper {
    private int firstVisible = 0;
    private int lastVisible = 0;
    private int visibleCount = 0;
    private int playId;
    private int rangeTop;
    private int rangeBottom;
    private PlayRunnable runnable;

    private WeakHandler playHandler = new WeakHandler();

    public ScrollCalculatorHelper(int playId, int rangeTop, int rangeBottom) {
        this.playId = playId;
        this.rangeTop = rangeTop;
        this.rangeBottom = rangeBottom;
    }

    public void onScrollStateChanged(RecyclerView view, int scrollState) {
        switch (scrollState) {
            case RecyclerView.SCROLL_STATE_IDLE://静止没有滚动(滚动停止)
                playVideo(view);
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING://正在被外部拖拽,一般为用户正在用手指滚动(手指拖动)
            case RecyclerView.SCROLL_STATE_SETTLING://自动滚动(惯性滚动)
        }
    }

    public void onScroll(RecyclerView view, int firstVisibleItem, int lastVisibleItem, int visibleItemCount) {
//        if (firstVisible == firstVisibleItem) {
//            return;
//        }
        firstVisible = firstVisibleItem;// 在屏幕可见区域的第一项位置
        lastVisible = lastVisibleItem;//在屏幕可见区域的最后一项位置
        visibleCount = visibleItemCount;//屏幕可见项的数目  用（2）减去（1）即可
    }


    void playVideo(RecyclerView view) {
        if (view == null) {
            return;
        }
        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
        VideoPlayer videoPlayer = null;
        boolean needPlay = false;
        for (int i = 0; i < visibleCount; i++) {
            VideoPlayer player = layoutManager.getChildAt(i).findViewById(playId);
            if (player != null) {
                Rect rect = new Rect();
                player.getLocalVisibleRect(rect);
                int height = player.getHeight();
                //说明第一个完全可视
                if (rect.top == 0 && rect.bottom == height) {
                    int[] screenPosition = new int[2];
                    player.getLocationOnScreen(screenPosition);
                    int halfHeight = player.getHeight() / 2;
                    int rangePosition = screenPosition[1] + halfHeight;
                    //中心点在播放区域内
                    if (rangePosition >= rangeTop && rangePosition <= rangeBottom) {
                        videoPlayer = player;
                        if (player.isIdle()) {
                            needPlay = true;
                        }
                        break;
                    }
                }
            }
        }
        if (videoPlayer != null && needPlay) {
            if (runnable != null) {
                playHandler.removeCallbacks(runnable);
                runnable = null;
            }
            runnable = new PlayRunnable(videoPlayer);
            //降低频率
            playHandler.postDelayed(runnable, 500);
        }


    }

    private class PlayRunnable implements Runnable {
        VideoPlayer videoPlayer;
        public PlayRunnable(VideoPlayer videoPlayer) {
            this.videoPlayer = videoPlayer;
        }

        @Override
        public void run() {
            videoPlayer.setNeedMute(true);
            videoPlayer.seekTo(1);
            videoPlayer.start();
        }
    }
}
