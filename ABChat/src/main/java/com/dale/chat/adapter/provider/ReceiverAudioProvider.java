package com.dale.chat.adapter.provider;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dale.abchat.R;
import com.dale.chat.bean.MsgAudio;
import com.dale.chat.bean.MultipleMsgEntity;
import com.dale.chat.ui.Const;
import com.dale.utils.LogUtils;
import com.dale.utils.SizeUtils;

public class ReceiverAudioProvider extends BaseProvider<MultipleMsgEntity, BaseViewHolder> {

    @Override
    public int viewType() {
        return MultipleMsgEntity.RECEIVER_AUDIO;
    }

    @Override
    public int layout() {
        return R.layout.receiver_item_audio_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, MultipleMsgEntity data, int position) {
        super.convert(helper,data,position);

        MsgAudio msgAudio = (MsgAudio) data.baseMsg;

        RelativeLayout wechat_receiver_audio = helper.getView(R.id.wechat_receiver_audio);
        ImageView ivAudio = helper.getView(R.id.ivAudio);
        TextView tvDuration = helper.getView(R.id.tvDuration);
        int increment = 120 * msgAudio.getTime()/ Const.DEFAULT_MAX_AUDIO_RECORD_TIME_SECOND;
        LogUtils.d("increment:" + increment);

        ViewGroup.LayoutParams params = wechat_receiver_audio.getLayoutParams();
        params.width = SizeUtils.dp2px(65) + SizeUtils.dp2px(increment);
        wechat_receiver_audio.setLayoutParams(params);

        //设置语音消息
        tvDuration.setText(String.format("%1$d''",msgAudio.getTime()));

    }
}
