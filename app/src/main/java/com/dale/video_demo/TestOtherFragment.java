package com.dale.video_demo;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dale.libdemo.R;
import com.dale.utils.ScreenUtils;
import com.dale.utils.SizeUtils;
import com.dale.video_demo.scroll.CallBack;
import com.dale.video_demo.scroll.ListCalculator;
import com.dale.video_demo.scroll.RecyclerViewGetter;
import com.zbj.videoplayer.manager.VideoPlayerManager;
import com.zbj.videoplayer.player.VideoPlayer;

import java.util.ArrayList;
import java.util.List;


/**
 * 如果你需要在播放的时候按下Home键能暂停，回调此Fragment又继续的话，需要继承自CompatHomeKeyFragment
 * @author yc
 */
public class TestOtherFragment extends Fragment implements CallBack {

    private RecyclerView mRecyclerView;
    ListCalculator calculator;
    boolean mFull = false;
    LinearLayoutManager linearLayoutManager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        calculator = new ListCalculator(new RecyclerViewGetter(linearLayoutManager, mRecyclerView), this);
    }

    private void init(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager( linearLayoutManager = new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        List<Video> list = new ArrayList<>();
        for (int a = 0; a< ConstantVideo.VideoPlayerList.length ; a++){
            Video video = new Video(ConstantVideo.VideoPlayerTitle[a],ConstantVideo.VideoPlayerList[a]);
            list.add(video);
        }
        VideoAdapter adapter = new VideoAdapter(getActivity(), list);
        mRecyclerView.setAdapter(adapter);


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            int newState = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                this.newState = newState;
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    calculator.onScrolled(300);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                calculator.onScrolling(newState);
            }
        });

    }


    //当前的item 滚动结束调用
    @Override
    public void activeOnScrolled(View newActiveView, int position) {
        VideoPlayer videoPlayer = (VideoPlayer) newActiveView.findViewById(R.id.nice_video_player);
        if (videoPlayer != null){
            videoPlayer.setNeedMute(true);
            videoPlayer.seekTo(1);
            videoPlayer.start();
        }




        Log.d("activeOnScrolled", "" + position);
    }

    //当前的item 滚动中调用
    @Override
    public void activeOnScrolling(View newActiveView, int position) {
        Log.d("activeOnScrolled", "" + position);
//        ObjectAnimator animator = ObjectAnimator.ofFloat(newActiveView, "alpha", 0.3f, 1);
//        animator.setDuration(300);
//        animator.start();
    }

    //销毁的item
    @Override
    public void deactivate(View currentView, int position) {
        VideoPlayer videoPlayer = (VideoPlayer) currentView.findViewById(R.id.nice_video_player);
        if (videoPlayer != null){
            VideoPlayerManager.instance().releaseVideoPlayer();
        }

        Log.d("deactivate", "" + position);
//        ObjectAnimator animator = ObjectAnimator.ofFloat(currentView, "alpha", 1, 0.3f);
//        animator.setDuration(300);
//        animator.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        VideoPlayerManager.instance().releaseVideoPlayer();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (newConfig.orientation != ActivityInfo.SCREEN_ORIENTATION_USER) {
            mFull = false;
        } else {
            mFull = true;
        }
    }
}
