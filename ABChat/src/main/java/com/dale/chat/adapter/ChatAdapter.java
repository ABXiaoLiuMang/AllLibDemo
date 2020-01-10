package com.dale.chat.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.dale.chat.adapter.provider.ReceiverImageProvider;
import com.dale.chat.adapter.provider.ReceiverTextProvider;
import com.dale.chat.adapter.provider.SenderImageProvider;
import com.dale.chat.adapter.provider.SenderTextProvider;
import com.dale.chat.bean.MultipleMsgEntity;

public class ChatAdapter extends MultipleItemRvAdapter<MultipleMsgEntity, BaseViewHolder> {

    public ChatAdapter() {
        super(null);
        //构造函数若有传其他参数可以在调用finishInitialize()之前进行赋值，赋值给全局变量
        //这样getViewType()和registerItemProvider()方法中可以获取到传过来的值
        //getViewType()中可能因为某些业务逻辑，需要将某个值传递过来进行判断，返回对应的viewType
        //registerItemProvider()中可以将值传递给ItemProvider
        finishInitialize();
    }

    @Override
    protected int getViewType(MultipleMsgEntity entity) {
        return entity.type;
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProvider(new ReceiverTextProvider());
        mProviderDelegate.registerProvider(new SenderTextProvider());
        mProviderDelegate.registerProvider(new ReceiverImageProvider());
        mProviderDelegate.registerProvider(new SenderImageProvider());
    }
}
