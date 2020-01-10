package com.dale.chat.ui;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.abchat.R;
import com.dale.audio.AudioRecordManager;
import com.dale.audio.IAudioRecordListener;
import com.dale.chat.bean.MultipleMsgEntity;
import com.dale.constant.PermissionConstants;
import com.dale.emoji.EmotionKeyboard;
import com.dale.emoji.EmotionLayout;
import com.dale.emoji.IEmotionExtClickListener;
import com.dale.emoji.IEmotionSelectedListener;
import com.dale.framework.ui.ABBaseActivity;
import com.dale.framework.ui.BasePresenter;
import com.dale.utils.LogUtils;
import com.dale.utils.PermissionUtils;
import com.dale.utils.ResUtils;
import com.dale.utils.StatusBarUtil;
import com.dale.utils.StringUtils;
import com.dale.utils.ToastUtils;
import com.dale.utils.WeakHandler;

import java.io.File;


public abstract class ABChatActivity<T extends MultipleMsgEntity, P extends BasePresenter> extends ABBaseActivity<P> implements IEmotionSelectedListener,
        BaseQuickAdapter.OnItemClickListener, IEmotionExtClickListener,IAudioRecordListener {

    protected EditText mEtContent;
    protected LinearLayout mLlRoot;
    protected LinearLayout mLlContent;
    protected LinearLayout mFlEmotionView;//表情输入父布局
    protected EmotionLayout mElEmotion;//表情布局
    protected LinearLayout mLlMore;//地图，红包等更多布局
    protected ImageView mIvEmo;//笑脸按钮
    protected ImageView mIvMore;//加号更多按钮
    protected ImageView mIvAudio;//点击显示按住录音按钮
    protected TextView mBtnAudio;//按住录音按钮
    protected TextView mBtnSend;//发送按钮
    protected TextView backView;//返回按钮
    protected TextView tv_title;//标题
    protected View headView;
    protected EmotionKeyboard mEmotionKeyboard;
    protected AudioRecordManager audioRecordManager;

    protected RecyclerView recyclerView;
    protected BaseQuickAdapter<T, BaseViewHolder> listAdapter;

    public abstract BaseQuickAdapter<T, BaseViewHolder> getListAdapter();

    public abstract String getTopTitle();

    public abstract void onScrollTop();

    public abstract void sendMsg(String msg);

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat_layout;
    }

    @Override
    protected void initSystemBar() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            View decorView = getWindow().getDecorView();
            int vis = decorView.getSystemUiVisibility();
            vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(vis);
        }
        StatusBarUtil.setColor(this, ResUtils.getColor(R.color.chat_title_bg), 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        mEtContent.clearFocus();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initViewsAndEvents() {
        tv_title = findViewById(R.id.tv_title);
        backView = findViewById(R.id.backView);
        mLlRoot = findViewById(R.id.llRoot);
        mEtContent = findViewById(R.id.etContent);
        mLlContent = findViewById(R.id.llContent);
        mFlEmotionView = findViewById(R.id.flEmotionView);
        mElEmotion = findViewById(R.id.elEmotion);
        mIvEmo = findViewById(R.id.ivEmo);
        mIvMore = findViewById(R.id.ivMore);
        mLlMore = findViewById(R.id.llMore);
        mBtnAudio = findViewById(R.id.btnAudio);
        mIvAudio = findViewById(R.id.ivAudio);
        mBtnSend = findViewById(R.id.btnSend);
        recyclerView = findViewById(R.id.recyclerview);
        mElEmotion.attachEditText(mEtContent);
        audioRecordManager = AudioRecordManager.getInstance(getApplicationContext());
        initListener();
        initRecyclerView();
        initEmotionKeyboard();

        PermissionUtils.permission(PermissionConstants.STORAGE,PermissionConstants.MICROPHONE).callback(new PermissionUtils.SimpleCallback() {
            @Override
            public void onGranted() {
                initAudioRecordManager();
            }

            @Override
            public void onDenied() {
                ToastUtils.showLong("权限不足");
                finish();
            }
        }).request();
    }

    protected void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setStackFromEnd(true);
        listAdapter = getListAdapter();
        listAdapter.addHeaderView(headView = getHeaderView());
        listAdapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                if (firstCompletelyVisibleItemPosition == 0) {//滑动到顶部
                    onScrollTop();
                }

                int lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1) { //滑动到顶部
                }
            }
        });
    }

    protected View getHeaderView() {
        return View.inflate(mContext, R.layout.item_chat_head_layout, null);
    }

    @SuppressLint("ClickableViewAccessibility")
    public void initListener() {
        backView.setOnClickListener(v -> finish());
        tv_title.setText(getTopTitle());
        //点击录音切换界面
        mIvAudio.setOnClickListener(v -> {
            if (mBtnAudio.isShown()) {
                hideAudioButton();
                mEtContent.requestFocus();
                if (mEmotionKeyboard != null) {
                    mEmotionKeyboard.showSoftInput();
                }
            } else {
                showAudioButton();
                hideEmotionLayout();
                hideMoreLayout();
            }

            postTaskDelay(() -> smoothMoveToPosition(listAdapter.getItemCount() - 1), 50);
        });

        mLlContent.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    closeBottomAndKeyboard();
                    break;
            }
            return false;
        });

        //触摸列表，关闭键盘
        recyclerView.setOnTouchListener((v, event) -> {
            closeBottomAndKeyboard();
            return false;
        });

        mElEmotion.setEmotionSelectedListener(this);
        mElEmotion.setEmotionAddVisiable(true);
        mElEmotion.setEmotionSettingVisiable(true);
        mElEmotion.setEmotionExtClickListener(this);

        mEtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mEtContent.getText().toString().trim().length() > 0) {
                    mBtnSend.setVisibility(View.VISIBLE);
                    mIvMore.setVisibility(View.GONE);
                } else {
                    mBtnSend.setVisibility(View.GONE);
                    mIvMore.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mBtnSend.setOnClickListener(v -> {
            String msg = mEtContent.getText().toString();
            if (!StringUtils.isEmpty(msg)) {
                sendMsg(msg);
                mEtContent.setText("");
            }
        });
    }

    private void initEmotionKeyboard() {
        mEmotionKeyboard = EmotionKeyboard.with(this);
        mEmotionKeyboard.bindToEditText(mEtContent);
        mEmotionKeyboard.bindToContent(mLlContent);
        mEmotionKeyboard.setEmotionLayout(mFlEmotionView);
        mEmotionKeyboard.bindToEmotionButton(mIvEmo, mIvMore);
        mEmotionKeyboard.setOnEmotionButtonOnClickListener(view -> {
            if (view.getId() == R.id.ivEmo) {
                postTaskDelay(() -> smoothMoveToPosition(listAdapter.getItemCount() - 1), 50);
                mEtContent.clearFocus();
                if (!mElEmotion.isShown()) {
                    if (mLlMore.isShown()) {
                        showEmotionLayout();
                        hideMoreLayout();
                        hideAudioButton();
                        return true;
                    }
                } else if (mElEmotion.isShown() && !mLlMore.isShown()) {
                    mIvEmo.setImageResource(R.mipmap.ic_cheat_emo);
                    return false;
                }
                showEmotionLayout();
                hideMoreLayout();
                hideAudioButton();
            } else if (view.getId() == R.id.ivMore) {
                postTaskDelay(() -> smoothMoveToPosition(listAdapter.getItemCount() - 1), 50);
                mEtContent.clearFocus();
                if (!mLlMore.isShown()) {
                    if (mElEmotion.isShown()) {
                        showMoreLayout();
                        hideEmotionLayout();
                        hideAudioButton();
                        return true;
                    }
                }
                showMoreLayout();
                hideEmotionLayout();
                hideAudioButton();
            }
            return false;
        });
    }

    private void postTaskDelay(Runnable task, int delayMillis) {
        new WeakHandler().postDelayed(task, delayMillis);
    }

    /**
     * 平滑滚动到指定位置（注意：对瀑布流无效果）
     */
    public void smoothMoveToPosition(int position) {

        if (position < 0 || position >= listAdapter.getItemCount()) {
            LogUtils.e("CSDN_LQR", "超出范围了");
            return;
        }
        recyclerView.stopScroll();
        LinearLayoutManager glm = (LinearLayoutManager) recyclerView.getLayoutManager();
        int firstItem = glm.findFirstVisibleItemPosition();
        int lastItem = glm.findLastVisibleItemPosition();
        if (position <= firstItem) {
            recyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            int top = recyclerView.getChildAt(position - firstItem).getTop();
            recyclerView.smoothScrollBy(0, top);
        } else {
            recyclerView.smoothScrollToPosition(position);
        }
    }


    private void showEmotionLayout() {
        mElEmotion.setVisibility(View.VISIBLE);
        mIvEmo.setImageResource(R.mipmap.ic_cheat_keyboard);
    }

    private void hideEmotionLayout() {
        mElEmotion.setVisibility(View.GONE);
        mIvEmo.setImageResource(R.mipmap.ic_cheat_emo);
    }

    private void showMoreLayout() {
        mLlMore.setVisibility(View.VISIBLE);
    }

    private void hideMoreLayout() {
        mLlMore.setVisibility(View.GONE);
    }

    private void hideAudioButton() {
        mBtnAudio.setVisibility(View.GONE);
        mEtContent.setVisibility(View.VISIBLE);
        mIvAudio.setImageResource(R.mipmap.ic_cheat_voice);
    }

    private void showAudioButton() {
        mBtnAudio.setVisibility(View.VISIBLE);
        mEtContent.setVisibility(View.GONE);
        mIvAudio.setImageResource(R.mipmap.ic_cheat_keyboard);

        if (mFlEmotionView.isShown()) {
            if (mEmotionKeyboard != null) {
                mEmotionKeyboard.interceptBackPress();
            }
        } else {
            if (mEmotionKeyboard != null) {
                mEmotionKeyboard.hideSoftInput();
            }
        }
    }

    private void closeBottomAndKeyboard() {
        mElEmotion.setVisibility(View.GONE);
        mLlMore.setVisibility(View.GONE);
        if (mEmotionKeyboard != null) {
            mEmotionKeyboard.hideSoftInput();//我修改
            mEmotionKeyboard.interceptBackPress();
            mIvEmo.setImageResource(R.mipmap.ic_cheat_emo);
        }
    }



/***********以下都是录音出来逻辑************/
    @SuppressLint("ClickableViewAccessibility")
    private void initAudioRecordManager() {
        audioRecordManager.setMaxVoiceDuration(Const.DEFAULT_MAX_AUDIO_RECORD_TIME_SECOND);
        File audioDir = new File(Const.AUDIO_SAVE_DIR);
        if (!audioDir.exists()) {
            audioDir.mkdirs();
        }
        audioRecordManager.setAudioSavePath(audioDir.getAbsolutePath());
        audioRecordManager.setAudioRecordListener(this);

        mBtnAudio.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    audioRecordManager.startRecord();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (isCancelled(v, event)) {
                        audioRecordManager.willCancelRecord();
                    } else {
                        audioRecordManager.continueRecord();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    audioRecordManager.stopRecord();
                    audioRecordManager.destroyRecord();
                    break;
            }
            return false;
        });
    }

    private TextView mTimerTV;
    private TextView mStateTV;
    private ImageView mStateIV;
    private PopupWindow mRecordWindow;

    @Override
    public void initTipView() {
        View view = View.inflate(mContext, R.layout.popup_audio_wi_vo, null);
        mStateIV = view.findViewById(R.id.rc_audio_state_image);
        mStateTV = view.findViewById(R.id.rc_audio_state_text);
        mTimerTV = view.findViewById(R.id.rc_audio_timer);
        mRecordWindow = new PopupWindow(view, -1, -1);
        mRecordWindow.showAtLocation(mLlRoot, 17, 0, 0);
        mRecordWindow.setFocusable(true);
        mRecordWindow.setOutsideTouchable(false);
        mRecordWindow.setTouchable(false);
    }

    @Override
    public void setTimeoutTipView(int counter) {
        if (this.mRecordWindow != null) {
            this.mStateIV.setVisibility(View.GONE);
            this.mStateTV.setVisibility(View.VISIBLE);
            this.mStateTV.setText(R.string.voice_rec);
            this.mStateTV.setBackgroundResource(R.drawable.bg_voice_popup);
            this.mTimerTV.setText(String.format("%s", new Object[]{Integer.valueOf(counter)}));
            this.mTimerTV.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setRecordingTipView() {
        if (this.mRecordWindow != null) {
            this.mStateIV.setVisibility(View.VISIBLE);
            this.mStateIV.setImageResource(R.mipmap.ic_volume_1);
            this.mStateTV.setVisibility(View.VISIBLE);
            this.mStateTV.setText(R.string.voice_rec);
            this.mStateTV.setBackgroundResource(R.drawable.bg_voice_popup);
            this.mTimerTV.setVisibility(View.GONE);
        }
    }

    @Override
    public void setAudioShortTipView() {
        if (this.mRecordWindow != null) {
            mStateIV.setImageResource(R.mipmap.ic_volume_wraning);
            mStateTV.setText(R.string.voice_short);
        }
    }

    @Override
    public void setCancelTipView() {
        if (this.mRecordWindow != null) {
            this.mTimerTV.setVisibility(View.GONE);
            this.mStateIV.setVisibility(View.VISIBLE);
            this.mStateIV.setImageResource(R.mipmap.ic_volume_cancel);
            this.mStateTV.setVisibility(View.VISIBLE);
            this.mStateTV.setText(R.string.voice_cancel);
            this.mStateTV.setBackgroundResource(R.drawable.corner_voice_style);
        }
    }

    @Override
    public void destroyTipView() {
        if (this.mRecordWindow != null) {
            this.mRecordWindow.dismiss();
            this.mRecordWindow = null;
            this.mStateIV = null;
            this.mStateTV = null;
            this.mTimerTV = null;
        }
    }

    @Override
    public void onAudioDBChanged(int db) {
        switch (db / 5) {
            case 0:
                this.mStateIV.setImageResource(R.mipmap.ic_volume_1);
                break;
            case 1:
                this.mStateIV.setImageResource(R.mipmap.ic_volume_2);
                break;
            case 2:
                this.mStateIV.setImageResource(R.mipmap.ic_volume_3);
                break;
            case 3:
                this.mStateIV.setImageResource(R.mipmap.ic_volume_4);
                break;
            case 4:
                this.mStateIV.setImageResource(R.mipmap.ic_volume_5);
                break;
            case 5:
                this.mStateIV.setImageResource(R.mipmap.ic_volume_6);
                break;
            case 6:
                this.mStateIV.setImageResource(R.mipmap.ic_volume_7);
                break;
            default:
                this.mStateIV.setImageResource(R.mipmap.ic_volume_8);
        }
    }


    private boolean isCancelled(View view, MotionEvent event) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);

        if (event.getRawX() < location[0] || event.getRawX() > location[0] + view.getWidth()
                || event.getRawY() < location[1] - 40) {
            return true;
        }

        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AudioRecordManager.getInstance(mContext).onDestroy();
    }
}
