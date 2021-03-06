//package com.dale.framework.ui;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.dale.framework.R;
//import com.lxj.xpopup.XPopup;
//import com.lxj.xpopup.impl.LoadingPopupView;
//
//import butterknife.ButterKnife;
//import butterknife.Unbinder;
//
///**
// * create by Dale
// * create on 2019/5/17
// * description:所有Fragment基类
// */
//public abstract class ABBaseFragment extends Fragment {
//    protected Activity mContext;
//    protected Bundle bundle;
//    protected View rootView;
//    protected Unbinder unbinder;
//    protected LoadingPopupView progressDialog;
//
//    protected abstract int getLayoutId();
//
//    protected abstract void initPresenters();
//
//    protected abstract void initViewsAndEvents();
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        bundle = getArguments();
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        mContext = getActivity();
//        if (rootView == null) {
//            rootView = inflater.inflate(getLayoutId(), container, false);
//        } else {
//            ViewGroup parent = (ViewGroup) rootView.getParent();
//            if (parent != null) {
//                parent.removeView(rootView);
//            }
//            if (container != null) {
//                container.removeView(rootView);
//            }
//        }
//
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        initPresenters();
//        initViewsAndEvents();
//    }
//
//    protected void showProgressDialog() {
//        if (progressDialog == null) {
//            progressDialog = new XPopup.Builder(getActivity())
//                    .asLoading();
//        }
//        progressDialog.show();
//    }
//
//    protected void dismissProgressDialog() {
//        if (progressDialog != null) {
//            progressDialog.dismiss();
//        }
//    }
//
//
//    public void goActivity(Class<? extends Activity> descClass) {
//        this.goActivity(descClass, null);
//    }
//
//    public void goActivity(Class<? extends Activity> descClass, Bundle bundle) {
//        try {
//            Intent intent = new Intent();
//            intent.setClass(mContext, descClass);
//            if (bundle != null) {
//                intent.putExtras(bundle);
//            }
//            mContext.startActivityForResult(intent, 0);
//            mContext.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//        } catch (Exception e) {
//        }
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        if (unbinder != null) {
//            unbinder.unbind();
//        }
//    }
//
//}
//
