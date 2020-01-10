package com.dale.chat.bean;

public class MultipleMsgEntity {

    /**
     * 文本
     */
    public static final int RECEIVER_TEXT = 1;
    public static final int SENDER_TEXT = 2;

    /**
     * 本地git表情 和本地图片文件
     */
    public static final int RECEIVER_IMAGE = 3;
    public static final int SENDER_IMAGE = 4;



    public int type;
    public MsgData msgData;

    public MultipleMsgEntity(int type) {
        this.type = type;
    }

    public MultipleMsgEntity(int type, MsgData msgData) {
        this.type = type;
        this.msgData = msgData;
    }
}
