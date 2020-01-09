package com.dale.chat.ui;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.abchat.R;
import com.dale.chat.bean.MultipleMsgEntity;
import com.dale.emoji.EmotionKeyboard;
import com.dale.emoji.EmotionLayout;
import com.dale.emoji.IEmotionExtClickListener;
import com.dale.emoji.IEmotionSelectedListener;
import com.dale.framework.ui.ABBaseActivity;
import com.dale.framework.ui.BasePresenter;
import com.dale.utils.LogUtils;
import com.dale.utils.ResUtils;
import com.dale.utils.StatusBarUtil;
import com.dale.utils.StringUtils;
import com.dale.utils.WeakHandler;


public abstract class ABChatActivity<T extends MultipleMsgEntity, P extends BasePresenter> extends ABBaseActivity<P> implements IEmotionSelectedListener, BaseQuickAdapter.OnItemClickListener, IEmotionExtClickListener {

    protected EditText mEtContent;
    protected LinearLayout mLlContent;
    protected LinearLayout mFlEmotionView;//表情输入父布局
    protected EmotionLayout mElEmotion;//表情布局
    protected LinearLayout mLlMore;//地图，红包等更多布局
    protected ImageView mIvEmo;//笑脸按钮
    protected ImageView mIvMore;//加号更多按钮
    protected ImageView mIvAudio;//点击显示按住录音按钮
    protected Button mBtnAudio;//按住录音按钮
    protected Button mBtnSend;//按住录音按钮
    protected View headView;
    protected EmotionKeyboard mEmotionKeyboard;

    protected RecyclerView recyclerView;
    protected BaseQuickAdapter<T, BaseViewHolder> listAdapter;

    public abstract BaseQuickAdapter<T, BaseViewHolder> getListAdapter();

    public abstract void onScrollTop();

    public abstract void sendMsg(String msg);

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat_layout;
    }

    @Override
    protected void initSystemBar() {
        StatusBarUtil.setColor(this, ResUtils.getColor(R.color.colorPrimary), 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        mEtContent.clearFocus();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initViewsAndEvents() {
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
        initEmotionKeyboard();
        initListener();
        initRecyclerView();
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

        //触摸列表，关闭键盘
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
            mEmotionKeyboard.interceptBackPress();
            mIvEmo.setImageResource(R.mipmap.ic_cheat_emo);
        }
    }


}
