package com.dale.chat.ui;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.chat.adapter.ChatAdapter;
import com.dale.chat.bean.MsgData;
import com.dale.chat.bean.MultipleMsgEntity;
import com.dale.framework.ui.BasePresenter;
import com.dale.utils.ToastUtils;
import com.dale.utils.WeakHandler;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends ABChatActivity<MultipleMsgEntity, BasePresenter>{

    int p = 0;

    @Override
    protected void initViewsAndEvents() {
        super.initViewsAndEvents();
        listAdapter.setNewData(testData());
    }

    @Override
    public BaseQuickAdapter<MultipleMsgEntity, BaseViewHolder> getListAdapter() {
        return new ChatAdapter();
    }

    @Override
    public void onScrollTop() {
        if(p < 5){
            new WeakHandler().postDelayed(() -> {
                p++;
                listAdapter.addData(0,testData());
                if(p >= 5){
                    headView.setVisibility(View.GONE);
                }
            },500);
        }

    }

    @Override
    public void sendMsg(String msg) {
        ToastUtils.showLong("发送的消息：" + msg);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onEmotionAddClick(View view) {
        ToastUtils.showLong("onEmotionAddClick:添加");
    }

    @Override
    public void onEmotionSettingClick(View view) {
        ToastUtils.showLong("onEmotionSettingClick:设置");
    }

    @Override
    public void onEmojiSelected(String key) {
       ToastUtils.showLong("onEmojiSelected:" + key);
    }

    @Override
    public void onStickerSelected(String categoryName, String stickerName, String stickerBitmapPath) {
       ToastUtils.showLong("发送文件:" + stickerBitmapPath);
    }

/******************** test code *******************/
    private List<MultipleMsgEntity> testData(){
        List<MultipleMsgEntity> list = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            MsgData msgData = new MsgData();
            msgData.setMsg("谁删除了好友邀请列表");
            msgData.setTimeStamp(System.currentTimeMillis());
            MultipleMsgEntity multipleMsgEntity = new MultipleMsgEntity(MultipleMsgEntity.RECEIVER_TEXT,msgData);
            list.add(multipleMsgEntity);

            msgData = new MsgData();
            msgData.setMsg("不是我删除的别问我");
            msgData.setTimeStamp(System.currentTimeMillis());
            multipleMsgEntity = new MultipleMsgEntity(MultipleMsgEntity.SENDER_TEXT,msgData);
            list.add(multipleMsgEntity);

            msgData = new MsgData();
            msgData.setMsg("我有没有问你，艹");
            msgData.setTimeStamp(System.currentTimeMillis());
            multipleMsgEntity = new MultipleMsgEntity(MultipleMsgEntity.RECEIVER_TEXT,msgData);
            list.add(multipleMsgEntity);

            msgData = new MsgData();
            msgData.setMsg("你艹个鸡巴，滚蛋");
            msgData.setTimeStamp(System.currentTimeMillis());
            multipleMsgEntity = new MultipleMsgEntity(MultipleMsgEntity.SENDER_TEXT,msgData);
            list.add(multipleMsgEntity);
        }
        return list;
    }
}
