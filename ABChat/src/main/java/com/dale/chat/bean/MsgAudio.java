package com.dale.chat.bean;

/**
 * 语音消息
 */
public class MsgAudio extends BaseMsg {
    private String uri;//语音路径
    private int time;//语音时长

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
