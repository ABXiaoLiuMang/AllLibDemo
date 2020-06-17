package com.dale.agentweb_demo;

import androidx.annotation.Nullable;

import com.dale.xweb.BaseAgentWebActivity;

public class AgentMainActivity extends BaseAgentWebActivity {
    
    @Nullable
    @Override
    protected String getUrl() {
        return "https://chat.5b3x6.com/chat/chatClient/chatbox.jsp?companyID=5889092&configID=3";
    }
}

