package com.dale.framework_demo.fragment;

import android.widget.TextView;

import com.dale.framework_demo.LiveDataManager;
import com.dale.framework.ui.ABBaseFragment;
import com.dale.framework_demo.ui.OtherPresenter;
import com.dale.libdemo.R;
import com.dale.net.callback.NetObserver;
import com.dale.net.exception.ErrorMessage;
import com.dale.utils.LogUtils;

public class Tab1Fragment extends ABBaseFragment {

    OtherPresenter otherPresenter;
    TextView text;

//    public static Tab1Fragment getInstance(Bundle bundle) {
//        Tab1Fragment fragment = new Tab1Fragment();
//        fragment.setArguments(bundle);
//        return fragment;
//    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab1;
    }

    @Override
    protected void initPresenters() {
        otherPresenter = new OtherPresenter(this);
    }


    @Override
    protected void initViewsAndEvents() {
        text = rootView.findViewById(R.id.text);
        LiveDataManager.getInstance().testPrice.observe(this, new NetObserver<String>() {

            @Override
            protected void onSuccess(String s) {
                text.setText("s->" + s);
                LogUtils.d("Tab1Fragment:" + s);
            }

            @Override
            protected void onError(ErrorMessage errorMessage) {
                text.setText("s->" + errorMessage.getMessage());

            }
        });
        otherPresenter.getHome();
    }

}
