package com.dale.chat.ui;

import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.abchat.R;
import com.dale.audio.AudioPlayManager;
import com.dale.audio.IAudioPlayListener;
import com.dale.chat.adapter.ChatAdapter;
import com.dale.chat.bean.BaseMsg;
import com.dale.chat.bean.MsgAudio;
import com.dale.chat.bean.MsgText;
import com.dale.chat.bean.MultipleMsgEntity;
import com.dale.chat.utils.TestUtils;
import com.dale.framework.ui.BasePresenter;
import com.dale.utils.WeakHandler;
import com.lzy.imagepicker.bean.ImageItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends ABChatActivity<MultipleMsgEntity, BasePresenter>{

    int p = 0;

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        listAdapter.addData(TestUtils.testData());
    }

    @Override
    public BaseQuickAdapter<MultipleMsgEntity, BaseViewHolder> getListAdapter() {
        return new ChatAdapter();
    }

    @Override
    public String getTopTitle() {
        return "范冰冰";
    }

    @Override
    public void onScrollTop() {
        if(p < 5){
            new WeakHandler().postDelayed(() -> {
                p++;
                listAdapter.addData(0,TestUtils.testData());
                if(p >= 5){
                    headView.setVisibility(View.GONE);
                }
            },500);
        }

    }

    @Override
    public void sendMsg(String msg) {
//        ToastUtils.showLong("发送的消息：" + msg);
        listAdapter.addData(TestUtils.createTestMsg(msg));
        recyclerView.scrollToPosition(listAdapter.getItemCount()-1);
    }

    @Override
    public void selectPic(ArrayList<ImageItem> imageItems) {
        for (ImageItem imageItem : imageItems){
            listAdapter.addData(TestUtils.createImageMsg(imageItem.path));
            recyclerView.scrollToPosition(listAdapter.getItemCount()-1);

        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        MultipleMsgEntity multipleMsgEntity = (MultipleMsgEntity) adapter.getItem(position);
        BaseMsg baseMsg = multipleMsgEntity.baseMsg;
        if(baseMsg instanceof MsgAudio){
            MsgAudio msgAudio = (MsgAudio) baseMsg;
            final ImageView ivAudio = view.findViewById(R.id.ivAudio);
            Uri uri = Uri.parse(msgAudio.getUri());
            AudioPlayManager.getInstance().startPlay(mContext, uri, new IAudioPlayListener() {
                @Override
                public void onStart(Uri var1) {
                    if (ivAudio != null && ivAudio.getBackground() instanceof AnimationDrawable) {
                        AnimationDrawable animation = (AnimationDrawable) ivAudio.getBackground();
                        animation.start();
                    }
                }

                @Override
                public void onStop(Uri var1) {
                    if (ivAudio != null && ivAudio.getBackground() instanceof AnimationDrawable) {
                        AnimationDrawable animation = (AnimationDrawable) ivAudio.getBackground();
                        animation.stop();
                        animation.selectDrawable(0);
                    }

                }

                @Override
                public void onComplete(Uri var1) {
                    if (ivAudio != null && ivAudio.getBackground() instanceof AnimationDrawable) {
                        AnimationDrawable animation = (AnimationDrawable) ivAudio.getBackground();
                        animation.stop();
                        animation.selectDrawable(0);
                    }
                }
            });


        }
    }

    @Override
    public void onEmotionAddClick(View view) {
//        ToastUtils.showLong("标签添加按钮");
    }

    @Override
    public void onEmotionSettingClick(View view) {
//        ToastUtils.showLong("标签设置按钮");
    }

    @Override
    public void onEmojiSelected(String key) {
//       ToastUtils.showLong("选中的标签");
    }

    @Override
    public void onStickerSelected(String categoryName, String stickerName, String stickerBitmapPath) {
//       ToastUtils.showLong("发送文件:" + stickerBitmapPath);
        listAdapter.addData(TestUtils.createStickerMsg(stickerBitmapPath));
        recyclerView.scrollToPosition(listAdapter.getItemCount()-1);
    }

    @Override
    public void onStartRecord() {
//        ToastUtils.showShort("开始录音");
//        RongIMClient.getInstance().sendTypingStatus(mConversationType, mSessionId, VoiceMessage.class.getAnnotation(MessageTag.class).value());
    }

    @Override
    public void onFinish(Uri audioPath, int duration) {
        //发送文件
        File file = new File(audioPath.getPath());
        if (file.exists()) {
            listAdapter.addData(TestUtils.createAudioMsg(file.getPath(),duration));
            recyclerView.scrollToPosition(listAdapter.getItemCount()-1);
//                    mPresenter.sendAudioFile(audioPath, duration);
        }
    }

}
