package com.dale.chat.adapter.provider;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.abchat.R;
import com.dale.chat.bean.MsgImage;
import com.dale.chat.bean.MultipleMsgEntity;
import com.dale.chat.ui.MyBitmapTransformation;
import com.dale.utils.LogUtils;
import com.dale.utils.SizeUtils;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

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

        Glide.with(mContext).load(msgImage.getUrl()).into(new CustomTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) wechat_sender_image.getLayoutParams();
                int width;
                int height;
                if(resource.getIntrinsicWidth() * 4 <  resource.getIntrinsicHeight() * 3){
                    width = SizeUtils.dp2px(120);
                    height = width * resource.getIntrinsicHeight() / resource.getIntrinsicWidth();
                }else {
                    height =  SizeUtils.dp2px(160);
                    width = height * resource.getIntrinsicWidth() / resource.getIntrinsicHeight();
                }
                LogUtils.d("w:" + resource.getIntrinsicWidth() + " h:" + resource.getIntrinsicHeight() + " sw:" + width + " sh:" + height);
                lp.width = width;
                lp.height = height;
                wechat_sender_image.setLayoutParams(lp);
                wechat_sender_image.setImageDrawable(resource);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {
            }
        });

    }
}
