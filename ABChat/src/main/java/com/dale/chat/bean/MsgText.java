package com.dale.chat.bean;

/**
 * 文本消息
 */
public class MsgText extends BaseMsg {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
