package me.yokeyword.fragmentation.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.DefaultVerticalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Base class for activities that use the support-based
 * {@link ISupportActivity} and
 * {@link AppCompatActivity} APIs.
 * <p>
 * Created by YoKey on 17/6/20.
 */
public class SupportActivity extends AppCompatActivity implements ISupportActivity {
    final SupportActivityDelegate mDelegate = new SupportActivityDelegate(this);

    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return mDelegate;
    }

    /**
     * Perform some extra transactions.
     * 额外的事务：自定义Tag，添加SharedElement动画，操作非回退栈Fragment
     */
    @Override
    public ExtraTransaction extraTransaction() {
        return mDelegate.extraTransaction();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDelegate.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDelegate.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        mDelegate.onDestroy();
        super.onDestroy();
    }

    /**
     * Note： return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    /**
     * 不建议复写该方法,请使用 {@link #onBackPressedSupport} 代替
     */
    @Override
    final public void onBackPressed() {
        mDelegate.onBackPressed();
    }

    /**
     * 该方法回调时机为,Activity回退栈内Fragment的数量 小于等于1 时,默认finish Activity
     * 请尽量复写该方法,避免复写onBackPress(),以保证SupportFragment内的onBackPressedSupport()回退事件正常执行
     */
    @Override
    public void onBackPressedSupport() {
        mDelegate.onBackPressedSupport();
    }

    /**
     * 获取设置的全局动画 copy
     *
     * @return FragmentAnimator
     */
    @Override
    public FragmentAnimator getFragmentAnimator() {
        return mDelegate.getFragmentAnimator();
    }

    /**
     * Set all fragments animation.
     * 设置Fragment内的全局动画
     */
    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        mDelegate.setFragmentAnimator(fragmentAnimator);
    }

    /**
     * Set all fragments animation.
     * 构建Fragment转场动画
     * <p/>
     * 如果是在Activity内实现,则构建的是Activity内所有Fragment的转场动画,
     * 如果是在Fragment内实现,则构建的是该Fragment的转场动画,此时优先级 > Activity的onCreateFragmentAnimator()
     *
     * @return FragmentAnimator对象
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return mDelegate.onCreateFragmentAnimator();
    }

    @Override
    public void post(Runnable runnable) {
        mDelegate.post(runnable);
    }

    /****************************************以下为可选方法(Optional methods)******************************************************/

    /**
     * 加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
     *
     * @param containerId 容器id
     * @param toFragment  目标Fragment
     */
    public void loadRootFragment(int containerId, @NonNull ISupportFragment toFragment) {
        mDelegate.loadRootFragment(containerId, toFragment);
    }

    public void loadRootFragment(int containerId, ISupportFragment toFragment, boolean addToBackStack, boolean allowAnimation) {
        mDelegate.loadRootFragment(containerId, toFragment, addToBackStack, allowAnimation);
    }

    /**
     * 加载多个同级根Fragment,类似Wechat, QQ主页的场景
     */
    public void loadMultipleRootFragment(int containerId, int showPosition, ISupportFragment... toFragments) {
        mDelegate.loadMultipleRootFragment(containerId, showPosition, toFragments);
    }

    /**
     * show一个Fragment,hide其他同栈所有Fragment
     * 使用该方法时，要确保同级栈内无多余的Fragment,(只有通过loadMultipleRootFragment()载入的Fragment)
     * <p>
     * 建议使用更明确的{@link #showHideFragment(ISupportFragment, ISupportFragment)}
     *
     * @param showFragment 需要show的Fragment
     */
    public void showHideFragment(ISupportFragment showFragment) {
        mDelegate.showHideFragment(showFragment);
    }

    /**
     * show一个Fragment,hide一个Fragment ; 主要用于类似微信主页那种 切换tab的情况
     */
    public void showHideFragment(ISupportFragment showFragment, ISupportFragment hideFragment) {
        mDelegate.showHideFragment(showFragment, hideFragment);
    }

    /**
     * 启动新的Fragment
     */
    public void start(Class<? extends ISupportFragment> toClass) {
        mDelegate.start(getFragmentByCls(toClass));
    }
    public void start(Class<? extends ISupportFragment> toClass, Bundle bundle) {
        mDelegate.start(getFragmentByCls(toClass,bundle));
    }

    /**
     * 以某种启动模式，启动新的Fragment
     *
     * @param launchMode Similar to Activity's LaunchMode.
     */
    public void start(Class<? extends ISupportFragment> toClass, @ISupportFragment.LaunchMode int launchMode) {
        mDelegate.start(getFragmentByCls(toClass), launchMode);
    }
    public void start(Class<? extends ISupportFragment> toClass, Bundle bundle, @ISupportFragment.LaunchMode int launchMode) {
        mDelegate.start(getFragmentByCls(toClass,bundle), launchMode);
    }

    /**
     * 启动新的Fragment，并能接收到新Fragment的数据返回
     */
    public void startForResult(Class<? extends ISupportFragment> toClass, int requestCode) {
        mDelegate.startForResult(getFragmentByCls(toClass), requestCode);
    }
    public void startForResult(Class<? extends ISupportFragment> toClass, Bundle bundle, int requestCode) {
        mDelegate.startForResult(getFragmentByCls(toClass,bundle), requestCode);
    }

    /**
     * 启动目标Fragment，并关闭当前Fragment；不要尝试pop()+start()，动画会有问题
     */
    public void startWithPop(Class<? extends ISupportFragment> toClass) {
        mDelegate.startWithPop(getFragmentByCls(toClass));
    }
    public void startWithPop(Class<? extends ISupportFragment> toClass, Bundle bundle) {
        mDelegate.startWithPop(getFragmentByCls(toClass,bundle));
    }

    /**
     * 当前Fragment出栈(在当前Fragment所在栈内pop)
     */
    public void pop() {
        mDelegate.pop();
    }

    /**
     * 出栈到目标fragment
     *
     * @param targetFragmentClass   目标fragment
     * @param includeTargetFragment 是否包含该fragment
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment);
    }

    /**
     * 获取栈内的fragment对象
     */
    public <T extends ISupportFragment> T findFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getSupportFragmentManager(), fragmentClass);
    }


    private ISupportFragment getFragmentByCls(Class<? extends ISupportFragment> toClass,Bundle bundle){
        try{
            String stringClass = toClass.getCanonicalName();
            Class<?> fClass = Class.forName(stringClass);
            Object instance = fClass.newInstance();
            if (instance instanceof ISupportFragment){
                Fragment fragment = (Fragment)instance;
                if (bundle != null){
                    fragment.setArguments(bundle);
                }
                return (ISupportFragment) fragment;
            }
        }catch (Exception e){
            Log.e("Dream","跳转Fragment获取错误");
        }
        return null;
    }

    private ISupportFragment getFragmentByCls(Class<? extends ISupportFragment> toClass){
        return getFragmentByCls(toClass,null);
    }
}
