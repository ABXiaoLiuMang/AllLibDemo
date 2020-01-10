package com.dale.chat.adapter.provider;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.dale.abchat.R;
import com.dale.chat.bean.MsgData;
import com.dale.chat.bean.MultipleMsgEntity;
import com.dale.chat.utils.HelpUtils;

public abstract class BaseProvider<T extends MultipleMsgEntity,V extends BaseViewHolder> extends BaseItemProvider<T,V> {


    @Override
    public void convert(V helper, T data, int position) {
        MsgData currentMsgData = data.msgData;
        MsgData preMsgData = null;
        if(position > 0 && mData.size() > 0){
            preMsgData = mData.get(position - 1).msgData;
        }
        String showTime;
        if (preMsgData == null) {
            showTime = HelpUtils.calculateShowTime(HelpUtils.getCurrentMillisTime(), data.msgData.getTimeStamp());
        } else {
            showTime = HelpUtils.calculateShowTime(currentMsgData.getTimeStamp(), preMsgData.getTimeStamp());
        }
        TextView timeStamp = helper.getView(R.id.chat_time_stamp);
        if (showTime != null) {
            timeStamp.setVisibility(View.VISIBLE);
            timeStamp.setText(showTime);
        } else {
            timeStamp.setVisibility(View.GONE);
        }

        ImageView imageView = helper.getView(R.id.chat_head);
        Glide.with(mContext).load("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1406776478,3221057395&fm=26&gp=0.jpg").into(imageView);
    }
}
