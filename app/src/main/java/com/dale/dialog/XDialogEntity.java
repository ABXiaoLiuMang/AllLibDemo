package com.dale.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.FragmentActivity;

import com.lxj.xpopup.core.BasePopupView;

import java.util.Objects;

public class XDialogEntity {
    private View basePopupView;
    private int  priority;//有限级别  0最先展示

    public XDialogEntity(View basePopupView, int priority) {
        this.basePopupView = basePopupView;
        this.priority = priority;
    }

    public static XDialogEntity newEntity(View basePopupView, int priority) {
        return new XDialogEntity(basePopupView, priority);
    }

    public View getBasePopupView() {
        return basePopupView;
    }

    public XDialogEntity setBasePopupView(View basePopupView) {
        this.basePopupView = basePopupView;
        return this;
    }

    public int getPriority() {
        return priority;
    }

    public XDialogEntity setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        XDialogEntity that = (XDialogEntity) o;
        return priority == that.priority &&
                Objects.equals(basePopupView, that.basePopupView);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basePopupView, priority);
    }


    @Override
    public String toString() {
        return "XDialogEntity{" +
                "basePopupView=" + basePopupView +
                ", priority=" + priority +
                '}';
    }


    public void dismiss() {
        if (basePopupView instanceof BasePopupView) {
            ((BasePopupView) basePopupView).dismiss();
        } else {
            try {
                Context context = basePopupView.getContext();
                if (context instanceof FragmentActivity) {
                    FragmentActivity activity = (FragmentActivity) context;
                    FrameLayout decorView = (FrameLayout) activity.getWindow().getDecorView();
                    decorView.removeView(basePopupView);
                }
            } catch (Exception e) {
            }
        }
    }

    public void show() {
        if (basePopupView instanceof BasePopupView) {
            ((BasePopupView) basePopupView).show();
        } else {
            try {
                Context context = basePopupView.getContext();
                if (context instanceof FragmentActivity) {
                    FragmentActivity activity = (FragmentActivity) context;
                    FrameLayout decorView = (FrameLayout) activity.getWindow().getDecorView();
                    if (!basePopupView.isAttachedToWindow()) {
                        if (basePopupView.getParent() != null) {
                            ((ViewGroup) basePopupView.getParent()).removeView(basePopupView);
                        }
                        decorView.addView(basePopupView);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public boolean isShow() {
        if (basePopupView instanceof BasePopupView) {
            return ((BasePopupView) basePopupView).isShow();
        } else {
            return basePopupView.getVisibility() == View.VISIBLE;
        }
    }


}

