package com.dale.fragment_demo;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.dale.framework.ui.ABBaseFragment;
import com.dale.framework_demo.LiveDataManager;
import com.dale.framework_demo.ui.OtherContract;
import com.dale.framework_demo.ui.OtherPresenter;
import com.dale.libdemo.R;
import com.dale.net.callback.NetObserver;
import com.dale.net.exception.ErrorMessage;
import com.dale.utils.ToastUtils;
import com.dale.view.DivergeView;
import com.just.agentweb.Provider;

import java.util.ArrayList;

public class SecondFragment extends ABBaseFragment<OtherPresenter> implements OtherContract.IView {

    DivergeView mDivergeView;
    private ArrayList<Bitmap> mList;
    private int mIndex = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second;
    }

    @Override
    protected void initViewsAndEvents() {
        if (bundle != null && bundle.containsKey("TestKey")) {
            ToastUtils.showLong(bundle.getString("TestKey"));
        }

        mList = new ArrayList<>();
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.battery_10, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.battery_20, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.battery_50, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.battery_80, null)).getBitmap());
        mList.add(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.battery_100, null)).getBitmap());


        mDivergeView = rootView.findViewById(R.id.mDivergeView);

        rootView.findViewById(R.id.btn_test).setOnClickListener(v -> {
            presenter.getHome();
//            presenter.testOther();

            if (mIndex == 5) {
                mIndex = 0;
            }
            mDivergeView.startDiverges(mIndex);
            mIndex++;

        });

        mDivergeView.post(new Runnable() {
            @Override
            public void run() {
                mDivergeView.setEndPoint(new PointF(mDivergeView.getMeasuredWidth() / 2, 0));
                mDivergeView.setDivergeViewProvider(new Provider());
            }
        });

        LiveDataManager.getInstance().testPrice.observe(this, new NetObserver<String>() {

            @Override
            protected void onSuccess(String s) {
            }

            @Override
            protected void onError(ErrorMessage errorMessage) {
            }
        });
    }

    @Override
    public void test() {
        ToastUtils.showLong("hahahaha");
    }


    class Provider implements DivergeView.DivergeViewProvider {

        @Override
        public Bitmap getBitmap(Object obj) {
            return mList == null ? null : mList.get((int) obj);
        }
    }
}
