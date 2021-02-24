package com.dale.xweb;

import androidx.annotation.Nullable;


public class H5Activity extends BaseAgentWebActivity {
    @Nullable
    @Override
    protected String getUrl() {
        return "https://chat.7kr280.com/chat/chatClient/chatbox.jsp?companyID=5889098&configID=1";
    }

    @Override
    protected void createProvider() {

    }
}
