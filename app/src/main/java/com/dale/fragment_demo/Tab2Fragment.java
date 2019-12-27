package com.dale.fragment_demo;

import android.widget.TextView;

import com.dale.framework.tab.ABTabFragment;
import com.dale.framework_demo.LiveDataManager;
import com.dale.framework_demo.ui.OtherContract;
import com.dale.framework_demo.ui.OtherPresenter;
import com.dale.libdemo.R;
import com.dale.net.callback.NetObserver;
import com.dale.net.exception.ErrorMessage;
import com.dale.utils.ToastUtils;
import com.dale.view.RandomTextView;

public class Tab2Fragment extends ABTabFragment<OtherPresenter> implements OtherContract.IView  {


    TextView tv_test;
    RandomTextView mRandomTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab2;
    }

    @Override
    protected void initViewsAndEvents() {
        if(bundle != null && bundle.containsKey("TestKey")){
            ToastUtils.showLong(bundle.getString("TestKey"));
        }

        tv_test = rootView.findViewById(R.id.tv_test);

        rootView.findViewById(R.id.btn_test).setOnClickListener(v -> {
            presenter.getHome();
            presenter.testOther();

        });


        LiveDataManager.getInstance().testPrice.observe(this,new NetObserver<String>(){

            @Override
            protected void onSuccess(String s) {
                tv_test.setText("成功：" +s.substring(0,10));
            }

            @Override
            protected void onError(ErrorMessage errorMessage) {
                tv_test.setText("失败:" +errorMessage.getMessage());
            }
        });
    }

    @Override
    public void test() {
        ToastUtils.showLong("hahahaha");
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mRandomTextView = rootView.findViewById(R.id.mRandomTextView);
        mRandomTextView.setText("876543");
//        mRandomTextView.setPianyilian(RandomTextView.ALL);//所有位数相同速度滚动：
        mRandomTextView.setPianyilian(RandomTextView.FIRSTF_FIRST);//从左到右侧由快到慢滚动：
//        mRandomTextView.setPianyilian(RandomTextView.FIRSTF_LAST);//从左到右侧由慢到快滚动：
        mRandomTextView.start();
    }
}
