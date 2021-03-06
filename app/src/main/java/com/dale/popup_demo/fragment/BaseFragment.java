package com.dale.popup_demo.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dale.framework.view.StateLayout;
import com.dale.utils.ToastUtils;

/**
 * Description:
 * Create by dance, at 2018/12/9
 */
public abstract class BaseFragment extends Fragment {
    View view;
    boolean isInit = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(getLayoutId(), container, false);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        safeInit();
    }

    private void safeInit() {
        if (getUserVisibleHint() && view!=null) {
            if (!isInit) {
                isInit = true;
                init(view);
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        safeInit();
    }

    protected abstract int getLayoutId();
    public abstract void init(View view);

    public void toast(String msg) {
        ToastUtils.showLong(msg);
    }

}
