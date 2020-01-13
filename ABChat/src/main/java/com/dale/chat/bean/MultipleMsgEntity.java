package com.dale.chat.bean;

public class MultipleMsgEntity {

    /**
     * 文本
     */
    public static final int RECEIVER_TEXT = 1;
    public static final int SENDER_TEXT = 2;

    /**
     * 本地图片文件
     */
    public static final int RECEIVER_IMAGE = 3;
    public static final int SENDER_IMAGE = 4;

    /**
     * 本地git表情
     */
    public static final int RECEIVER_STICKER = 5;
    public static final int SENDER_STICKER = 6;


    /**
     * 语音
     */
    public static final int RECEIVER_AUDIO = 7;
    public static final int SENDER_AUDIO = 8;



    public int type;
    public BaseMsg baseMsg;

    public MultipleMsgEntity(int type) {
        this.type = type;
    }

    public MultipleMsgEntity(int type, BaseMsg baseMsg) {
        this.type = type;
        this.baseMsg = baseMsg;
    }
}
