package com.dale.agentweb_demo;

import androidx.annotation.Nullable;

import com.dale.xweb.BaseAgentWebActivity;

public class AgentMainActivity extends BaseAgentWebActivity {

    @Nullable
    @Override
    protected String getUrl() {
        return "https://chat.5b3x6.com/chat/chatClient/chatbox.jsp?companyID=5889092&configID=3";
    }

    @Override
    protected void createProvider() {

    }


//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_agent_main);
//    }
//
//    @NonNull
//    @Override
//    protected ViewGroup getAgentWebParent() {
//        return findViewById(R.id.layout);
//    }
//
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (mAgentWeb != null && mAgentWeb.handleKeyEvent(keyCode, event)) {
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }
//
//    @Override
//    protected int getIndicatorColor() {
//        return Color.parseColor("#ff0000");
//    }
//
//    @Override
//    protected void setTitle(WebView view, String title) {
//        super.setTitle(view, title);
//        if (!TextUtils.isEmpty(title)) {
//            if (title.length() > 10) {
//                title = title.substring(0, 10).concat("...");
//            }
//        }
////        mTitleTextView.setText(title);
//    }
//
//    @Override
//    protected int getIndicatorHeight() {
//        return 3;
//    }
//
//    @Nullable
//    @Override
//    protected String getUrl() {
//        return "https://chat.5b3x6.com/chat/chatClient/chatbox.jsp?companyID=5889092&configID=3";
//    }
}

