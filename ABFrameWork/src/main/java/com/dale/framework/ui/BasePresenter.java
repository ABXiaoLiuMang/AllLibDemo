//package com.dale.framework.ui;
//
//import androidx.annotation.CallSuper;
//import androidx.lifecycle.Lifecycle;
//import androidx.lifecycle.LifecycleEventObserver;
//import androidx.lifecycle.LifecycleOwner;
//
//import java.lang.ref.WeakReference;
//
///**
// * create by Dale
// * create on 2019/5/17
// * description: IView  MVP 的 V 层
// */
//public class BasePresenter<IView>{
//    private WeakReference<IView> mView;
//    private LifecycleOwner lifecycleOwner;
//    public BasePresenter(LifecycleOwner lifecycleOwner,IView view) {
//        this.lifecycleOwner = lifecycleOwner;
//        if(view != null){
//            this.mView = new WeakReference<>(view);
//        }
//        bindLifecycle(lifecycleOwner.getLifecycle());
//    }
//
//    public BasePresenter(LifecycleOwner lifecycleOwner) {
//       this(lifecycleOwner,null);
//    }
//
//    private void bindLifecycle(Lifecycle lifecycle){
//        lifecycle.addObserver((LifecycleEventObserver) (source, event) -> {
//            if (event == Lifecycle.Event.ON_CREATE) {
//                onCreate();
//            } else if (event == Lifecycle.Event.ON_START) {
//                onStart();
//            } else if (event == Lifecycle.Event.ON_RESUME) {
//                onResume();
//            } else if (event == Lifecycle.Event.ON_PAUSE) {
//                onPause();
//            } else if (event == Lifecycle.Event.ON_STOP) {
//                onStop();
//            } else if (event == Lifecycle.Event.ON_DESTROY) {
//                onDestroy();
//            }
//        });
//    }
//
//    @CallSuper
//    public void onCreate(){
//    }
//
//    @CallSuper
//    public void onStart(){
//    }
//
//    @CallSuper
//    public void onResume(){
//    }
//
//    @CallSuper
//    public void onPause(){
//    }
//
//    @CallSuper
//    public void onStop(){
//    }
//
//    @CallSuper
//    public void onDestroy(){
//        if (mView != null){
//            mView.clear();
//            mView = null;
//        }
//    }
//
//    /**
//     * desc: 获取当前View 可能为空,请做好非空判断
//     */
//    protected IView getView(){
//        if (mView == null) return null;
//        return mView.get();
//    }
//
//    protected LifecycleOwner getLifecycleOwner(){
//        return lifecycleOwner;
//    }
//
//}
//
