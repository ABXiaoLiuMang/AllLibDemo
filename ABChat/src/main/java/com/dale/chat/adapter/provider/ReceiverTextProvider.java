package com.dale.chat.adapter.provider;

import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.dale.abchat.R;
import com.dale.chat.bean.MsgData;
import com.dale.chat.bean.MultipleMsgEntity;
import com.dale.chat.ui.MoonUtils;
import com.dale.chat.utils.HelpUtils;
import com.dale.utils.LogUtils;

public class ReceiverTextProvider extends BaseProvider<MultipleMsgEntity, BaseViewHolder> {

    @Override
    public int viewType() {
        return MultipleMsgEntity.RECEIVER_TEXT;
    }

    @Override
    public int layout() {
        return R.layout.receiver_item_text_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, MultipleMsgEntity data, int position) {
        super.convert(helper,data,position);
        //设置消息文本
        MoonUtils.identifyFaceExpression(mContext,helper.getView(R.id.wechat_receiver_text),data.msgData.getMsg(), ImageSpan.ALIGN_BOTTOM);

    }
}
