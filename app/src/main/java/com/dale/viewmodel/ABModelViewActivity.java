package com.dale.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.dale.framework.R;
import com.dale.log.LogUtils;
import com.dale.utils.ExitUtils;
import com.dale.utils.KeyboardUtils;
import com.dale.utils.StatusBarUtil;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;

import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.ui.SupportActivity;

/**
 * create by Dale
 * create on 2019/5/17
 * description: 基类
 */
public abstract class ABModelViewActivity<P extends ViewModel> extends SupportActivity {
    protected P mViewModel;
    protected Activity mContext;
    protected Bundle bundle;
    protected Unbinder unbinder;
    protected LoadingPopupView progressDialog;

    protected abstract int getLayoutId();

    protected abstract void initViewsAndEvents();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        ExitUtils.getInstance().addActivity(this);//记录打开的Act
        initSystemBar();
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        bundle = getIntent().getExtras();
        initViewModel();
        initViewsAndEvents();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        ExitUtils.getInstance().removeActivity(this);//关闭的Act
    }

    protected void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new XPopup.Builder(this)
                    .asLoading();
        }
        progressDialog.show();

    }

    protected void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public void goActivity(Class<? extends Activity> descClass) {
        this.goActivity(descClass,null);
    }

    public void goActivity(Class<? extends Activity> descClass, Bundle bundle) {
        try {
            Intent intent = new Intent();
            intent.setClass(mContext, descClass);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            mContext.startActivityForResult(intent, 0);
            mContext.overridePendingTransition(R.anim.x_push_left_in, R.anim.x_push_left_out);
        } catch (Exception e) {
        }
    }


    @Override
    public void finish() {
        super.finish();
        KeyboardUtils.hideSoftInput(this);
        overridePendingTransition(R.anim.x_push_right_in, R.anim.x_push_right_out);
    }

    protected void initSystemBar(){
        StatusBarUtil.setTransparentForWindow(this);
        StatusBarUtil.setLightMode(this);//状态栏图标黑色
    }


    protected void initViewModel() {
        Class<P> entityClass =(Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        mViewModel =  new ViewModelProvider(this).get(entityClass);
    }


}
