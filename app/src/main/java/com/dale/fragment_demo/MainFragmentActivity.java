package com.dale.fragment_demo;

import android.os.Process;
import android.view.KeyEvent;

import com.dale.framework.ui.ABBaseActivity;
import com.dale.libdemo.R;
import com.dale.utils.ExitUtils;
import com.dale.utils.LogUtils;
import com.dale.utils.ToastUtils;

public class MainFragmentActivity extends ABBaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_fragment;
    }


    @Override
    protected void initViewsAndEvents() {
        if (findFragment(MainTabFragment.class) == null) {
            loadRootFragment(R.id.fl_container, MainTabFragment.newInstance());
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        LogUtils.d("cont:" +count);
        if (count > 1) {
            pop();
        } else {
            exitApp(2000);
        }
    }

    private long mExitTime;
    public void exitApp(long doubleClickTime) {
        try {
            if (doubleClickTime <= 0){
                Process.killProcess(android.os.Process.myPid());
                System.gc();
                System.exit(0);
            }else if ((System.currentTimeMillis() - mExitTime) > doubleClickTime) {
                ToastUtils.showLong("再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
                ExitUtils.getInstance().finishAll();
                Process.killProcess(android.os.Process.myPid());
                System.gc();
                System.exit(0);
            }
        } catch (Exception e) {
        }
    }


}
