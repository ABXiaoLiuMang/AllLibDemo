package com.dale.emotion;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dale.emoji.EmotionKeyboard;
import com.dale.emoji.EmotionLayout;
import com.dale.emoji.IEmotionExtClickListener;
import com.dale.emoji.IEmotionSelectedListener;
import com.dale.libdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * CSDN_LQR
 * 简单会话界面
 */
public class SimpleSessionActivity extends AppCompatActivity {

    @BindView(R.id.llContent)
    LinearLayout mLlContent;
    @BindView(R.id.etContent)
    EditText mEtContent;
    @BindView(R.id.ivEmo)
    ImageView mIvEmo;
    @BindView(R.id.elEmotion)
    EmotionLayout mElEmotion;

    private EmotionKeyboard mEmotionKeyboard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_session);
        ButterKnife.bind(this);

        initEmotionKeyboard();
        initView();
    }

    private void initView() {
        mElEmotion.attachEditText(mEtContent);
        mElEmotion.setEmotionAddVisiable(true);
        mElEmotion.setEmotionSettingVisiable(true);
        mElEmotion.setEmotionExtClickListener(new IEmotionExtClickListener() {
            @Override
            public void onEmotionAddClick(View view) {
                Toast.makeText(getApplicationContext(), "add", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEmotionSettingClick(View view) {
                Toast.makeText(getApplicationContext(), "setting", Toast.LENGTH_SHORT).show();
            }
        });

        mElEmotion.setEmotionSelectedListener(new IEmotionSelectedListener() {
            @Override
            public void onEmojiSelected(String key) {

            }

            @Override
            public void onStickerSelected(String categoryName, String stickerName, String stickerBitmapPath) {
                String stickerPath = stickerBitmapPath;
                Toast.makeText(getApplicationContext(), stickerPath, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initEmotionKeyboard() {
        mEmotionKeyboard = EmotionKeyboard.with(this);
        mEmotionKeyboard.bindToContent(mLlContent);
        mEmotionKeyboard.bindToEmotionButton(mIvEmo);
        mEmotionKeyboard.bindToEditText(mEtContent);
        mEmotionKeyboard.setEmotionLayout(mElEmotion);
    }
}