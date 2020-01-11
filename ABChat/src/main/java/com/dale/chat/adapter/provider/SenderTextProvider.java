package com.dale.chat.adapter.provider;

import android.text.style.ImageSpan;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.abchat.R;
import com.dale.chat.bean.MsgText;
import com.dale.chat.bean.MultipleMsgEntity;
import com.dale.chat.ui.MoonUtils;

public class SenderTextProvider extends BaseProvider<MultipleMsgEntity, BaseViewHolder> {

    @Override
    public int viewType() {
        return MultipleMsgEntity.SENDER_TEXT;
    }

    @Override
    public int layout() {
        return R.layout.sender_item_text_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, MultipleMsgEntity data, int position) {
        super.convert(helper, data, position);

        MsgText msgText = (MsgText) data.baseMsg;

        //设置消息文本
        MoonUtils.identifyFaceExpression(mContext,helper.getView(R.id.wechat_sender_text),msgText.getText(), ImageSpan.ALIGN_BOTTOM);

    }
}
