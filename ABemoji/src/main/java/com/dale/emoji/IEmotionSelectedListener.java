package com.dale.emoji;

public interface IEmotionSelectedListener {
    void onEmojiSelected(String key);

    void onStickerSelected(String categoryName, String stickerName, String stickerBitmapPath);
}
