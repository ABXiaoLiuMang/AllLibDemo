package com.dale.chat.adapter.provider;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.abchat.R;
import com.dale.chat.bean.MsgImage;
import com.dale.chat.bean.MultipleMsgEntity;

public class SenderImageProvider extends BaseProvider<MultipleMsgEntity, BaseViewHolder> {

    @Override
    public int viewType() {
        return MultipleMsgEntity.SENDER_IMAGE;
    }

    @Override
    public int layout() {
        return R.layout.sender_item_image_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, MultipleMsgEntity data, int position) {
        super.convert(helper, data, position);

        MsgImage msgImage = (MsgImage) data.baseMsg;

        ImageView wechat_sender_image = helper.getView(R.id.wechat_sender_image);
        Glide.with(mContext).load(msgImage.getUrl()).centerCrop().into(wechat_sender_image);

    }
}
