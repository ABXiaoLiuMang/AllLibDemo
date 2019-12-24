package com.dale.framework.ui;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import com.dale.utils.LogUtils;

import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * create by Dale
 * create on 2019/5/17
 * description: IView  MVP 的 V 层
 */
public class BasePresenter<IView>{
    private WeakReference<IView> mView;
    private Object object;
    private LifecycleOwner lifecycleOwner;

    public void initPresenter(LifecycleOwner lifecycleOwner,Object object) {
        this.object = object;
        this.lifecycleOwner = lifecycleOwner;

        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof ParameterizedType &&
                ((ParameterizedType) superClass).getActualTypeArguments().length > 0) {
            Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
            Class<IView> classType = (Class<IView>) type;
            try {
                IView view = classType.cast(object);
                this.mView = new WeakReference<>(view);
            } catch (ClassCastException e) {
                LogUtils.e(String.format("警告：建议%1$s实现%2$s接口，方便getView回调",object.getClass().getSimpleName(),classType.getName()));
            }
        }
        bindLifecycle(lifecycleOwner.getLifecycle());
    }

    /**
     * 绑定其它的presenter  不可绑定自身
     * @param childPresenters 子Presenter
     */
    public void attachChildPresenter(@NonNull BasePresenter... childPresenters){
        for (BasePresenter presenter: childPresenters) {
            presenter.initPresenter(lifecycleOwner,object);
        }
    }

    private void bindLifecycle(Lifecycle lifecycle){
        lifecycle.addObserver((LifecycleEventObserver) (source, event) -> {
            if (event == Lifecycle.Event.ON_CREATE) {
                onCreate();
            } else if (event == Lifecycle.Event.ON_START) {
                onStart();
            } else if (event == Lifecycle.Event.ON_RESUME) {
                onResume();
            } else if (event == Lifecycle.Event.ON_PAUSE) {
                onPause();
            } else if (event == Lifecycle.Event.ON_STOP) {
                onStop();
            } else if (event == Lifecycle.Event.ON_DESTROY) {
                onDestroy();
            }
        });
    }

    @CallSuper
    public void onCreate(){
    }

    @CallSuper
    public void onStart(){
    }

    @CallSuper
    public void onResume(){
    }

    @CallSuper
    public void onPause(){
    }

    @CallSuper
    public void onStop(){
    }

    @CallSuper
    public void onDestroy(){
        if (mView != null){
            mView.clear();
            mView = null;
        }
    }

    /**
     * desc: 获取当前View 可能为空,请做好非空判断
     */
    protected IView getView(){
        if (mView == null) return null;
        return mView.get();
    }

    protected LifecycleOwner getLifecycleOwner(){
        return lifecycleOwner;
    }


    /**
     * 子Presenter不可以为空,也不能是自身
     * 返回 true表示 子Presenter符合条件
     */
//    private void checkChildPresenter(BasePresenter presenter){
//        if (presenter == null){
//            throw new NullPointerException("子Presenter不可为空");
//        }
//
//        Class<?> myClass = this.getClass();
//        String childName = presenter.getClass().getName();
//        if (TextUtils.equals(myClass.getName(),childName)){
//            throw new IllegalArgumentException(String.format("%s不可绑定自身",childName));
//        }
//
//        Class<?> superclass = myClass.getSuperclass();
//        while (superclass != null) {
//            if(superclass.getName().equals("com.dale.framework.ui.BasePresenter")){
//                break;
//            }
//            superclass = superclass.getSuperclass();
//            if (TextUtils.equals(myClass.getName(),childName)){
//                throw new IllegalArgumentException(String.format("%s不可绑定自身",childName));
//            }
//        }
//    }

}

