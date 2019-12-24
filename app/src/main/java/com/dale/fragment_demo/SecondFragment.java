package com.dale.fragment_demo;

import android.widget.TextView;

import com.dale.framework.ui.ABBaseFragment;
import com.dale.framework_demo.LiveDataManager;
import com.dale.framework_demo.ui.OtherContract;
import com.dale.framework_demo.ui.OtherPresenter;
import com.dale.libdemo.R;
import com.dale.net.callback.NetObserver;
import com.dale.net.exception.ErrorMessage;
import com.dale.utils.ToastUtils;

public class SecondFragment extends ABBaseFragment<OtherPresenter> implements OtherContract.IView {

    TextView tv_test;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second;
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

        LiveDataManager.getInstance().testPrice.observeForever(new NetObserver<String>(){

            @Override
            protected void onSuccess(String s) {
                tv_test.setText("成功：" +s);
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
}
