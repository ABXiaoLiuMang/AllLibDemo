package com.dale.chat.utils;

import com.dale.chat.bean.MsgAudio;
import com.dale.chat.bean.MsgImage;
import com.dale.chat.bean.MsgText;
import com.dale.chat.bean.MultipleMsgEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试工具类
 */
public class TestUtils {

    public static List<MultipleMsgEntity> createTestMsg(String msg){
        ArrayList<MultipleMsgEntity> list = new ArrayList<>();

        MsgText msgData = new MsgText();
        msgData.setText(msg);
        msgData.setTimeStamp(System.currentTimeMillis());
        MultipleMsgEntity multipleMsgEntity = new MultipleMsgEntity(MultipleMsgEntity.SENDER_TEXT,msgData);
        list.add(multipleMsgEntity);

        msgData = new MsgText();
        msgData.setText(msg);
        msgData.setTimeStamp(System.currentTimeMillis());
        multipleMsgEntity = new MultipleMsgEntity(MultipleMsgEntity.RECEIVER_TEXT,msgData);
        list.add(multipleMsgEntity);
        return list;
    }

    public static List<MultipleMsgEntity> createImageMsg(String imagePath){
        ArrayList<MultipleMsgEntity> list = new ArrayList<>();

        MsgImage msgImage = new MsgImage();
        msgImage.setUrl(imagePath);
        msgImage.setTimeStamp(System.currentTimeMillis());
        MultipleMsgEntity multipleMsgEntity = new MultipleMsgEntity(MultipleMsgEntity.SENDER_IMAGE,msgImage);
        list.add(multipleMsgEntity);

        msgImage = new MsgImage();
        msgImage.setUrl(imagePath);
        msgImage.setTimeStamp(System.currentTimeMillis());
        multipleMsgEntity = new MultipleMsgEntity(MultipleMsgEntity.RECEIVER_IMAGE,msgImage);
        list.add(multipleMsgEntity);
        return list;
    }

    public static List<MultipleMsgEntity> createAudioMsg(String audioPath,int duration){
        ArrayList<MultipleMsgEntity> list = new ArrayList<>();

        MsgAudio msgAudio = new MsgAudio();
        msgAudio.setTime(duration);
        msgAudio.setUri(audioPath);
        msgAudio.setTimeStamp(System.currentTimeMillis());
        MultipleMsgEntity multipleMsgEntity = new MultipleMsgEntity(MultipleMsgEntity.SENDER_AUDIO,msgAudio);
        list.add(multipleMsgEntity);

        msgAudio = new MsgAudio();
        msgAudio.setTime(duration);
        msgAudio.setUri(audioPath);
        msgAudio.setTimeStamp(System.currentTimeMillis());
        multipleMsgEntity = new MultipleMsgEntity(MultipleMsgEntity.RECEIVER_AUDIO,msgAudio);
        list.add(multipleMsgEntity);
        return list;
    }

    public static List<MultipleMsgEntity> testData(){
        List<MultipleMsgEntity> list = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            MsgText msgData = new MsgText();
            msgData.setText("谁删除了好友邀请列表");
            msgData.setTimeStamp(System.currentTimeMillis());
            MultipleMsgEntity multipleMsgEntity = new MultipleMsgEntity(MultipleMsgEntity.RECEIVER_TEXT,msgData);
            list.add(multipleMsgEntity);

            msgData = new MsgText();
            msgData.setText("不是我删除的别问我");
            msgData.setTimeStamp(System.currentTimeMillis());
            multipleMsgEntity = new MultipleMsgEntity(MultipleMsgEntity.SENDER_TEXT,msgData);
            list.add(multipleMsgEntity);

            msgData = new MsgText();
            msgData.setText("我有没有问你，艹");
            msgData.setTimeStamp(System.currentTimeMillis());
            multipleMsgEntity = new MultipleMsgEntity(MultipleMsgEntity.RECEIVER_TEXT,msgData);
            list.add(multipleMsgEntity);

            msgData = new MsgText();
            msgData.setText("你艹个鸡巴，滚蛋");
            msgData.setTimeStamp(System.currentTimeMillis());
            multipleMsgEntity = new MultipleMsgEntity(MultipleMsgEntity.SENDER_TEXT,msgData);
            list.add(multipleMsgEntity);
        }
        return list;
    }
}
