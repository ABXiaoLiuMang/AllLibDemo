package com.dale.stateview_demo.state;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

public class Gloading {
    public static final int STATUS_LOADING = 1;
    public static final int STATUS_LOAD_SUCCESS = 2;
    public static final int STATUS_LOAD_FAILED = 3;
    public static final int STATUS_EMPTY_DATA = 4;

    private static volatile Gloading mDefault;
    private Adapter mAdapter;

    public interface Adapter {
        View getView(Holder holder, View convertView, int status);
    }

    private Gloading() { }

    public static Gloading from(Adapter adapter) {
        Gloading gloading = new Gloading();
        gloading.mAdapter = adapter;
        return gloading;
    }

    public static Gloading getDefault() {
        if (mDefault == null) {
            synchronized (Gloading.class) {
                if (mDefault == null) {
                    mDefault = new Gloading();
                }
            }
        }
        return mDefault;
    }

    public static void initDefault(Adapter adapter) {
        getDefault().mAdapter = adapter;
    }

    public Holder wrap(Activity activity) {
        ViewGroup wrapper = activity.findViewById(android.R.id.content);
        return new Holder(mAdapter, activity, wrapper);
    }

    public Holder wrap(View view) {
        FrameLayout wrapper = new FrameLayout(view.getContext());
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp != null) {
            wrapper.setLayoutParams(lp);
        }
        if (view.getParent() != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            int index = parent.indexOfChild(view);
            parent.removeView(view);
            parent.addView(wrapper, index);
        }
        FrameLayout.LayoutParams newLp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        wrapper.addView(view, newLp);
        return new Holder(mAdapter, view.getContext(), wrapper);
    }

    public Holder cover(View view) {
        ViewParent parent = view.getParent();
        if (parent == null) {
            throw new RuntimeException("view has no parent to show gloading as cover!");
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        FrameLayout wrapper = new FrameLayout(view.getContext());
        viewGroup.addView(wrapper, view.getLayoutParams());
        return new Holder(mAdapter, view.getContext(), wrapper);
    }

    public static class Holder {
        private Adapter mAdapter;
        private Context mContext;
        private Runnable mRetryTask;
        private View mCurStatusView;
        private ViewGroup mWrapper;
        private int curState;
        private SparseArray<View> mStatusViews = new SparseArray<>(4);
        private Object mData;

        private Holder(Adapter adapter, Context context, ViewGroup wrapper) {
            this.mAdapter = adapter;
            this.mContext = context;
            this.mWrapper = wrapper;
        }

        public Holder withRetry(Runnable task) {
            mRetryTask = task;
            return this;
        }

        public Holder withData(Object data) {
            this.mData = data;
            return this;
        }

        /** show UI for status: {@link #STATUS_LOADING} */
        public void showLoading() {
            showLoadingStatus(STATUS_LOADING);
        }
        /** show UI for status: {@link #STATUS_LOAD_SUCCESS} */
        public void showLoadSuccess() {
            showLoadingStatus(STATUS_LOAD_SUCCESS);
        }
        /** show UI for status: {@link #STATUS_LOAD_FAILED} */
        public void showLoadFailed() {
            showLoadingStatus(STATUS_LOAD_FAILED);
        }
        /** show UI for status: {@link #STATUS_EMPTY_DATA} */
        public void showEmpty() {
            showLoadingStatus(STATUS_EMPTY_DATA);
        }

        public void showLoadingStatus(int status) {
            if (curState == status || !validate()) {
                return;
            }
            curState = status;
            View convertView = mStatusViews.get(status);
            if (convertView == null) {
                convertView = mCurStatusView;
            }
            try {
                View view = mAdapter.getView(this, convertView, status);
                if (view == null) {
                    return;
                }
                if (view != mCurStatusView || mWrapper.indexOfChild(view) < 0) {
                    if (mCurStatusView != null) {
                        mWrapper.removeView(mCurStatusView);
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.setElevation(Float.MAX_VALUE);
                    }
                    mWrapper.addView(view);
                    ViewGroup.LayoutParams lp = view.getLayoutParams();
                    if (lp != null) {
                        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
                        lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    }
                } else if (mWrapper.indexOfChild(view) != mWrapper.getChildCount() - 1) {
                    view.bringToFront();
                }
                mCurStatusView = view;
                mStatusViews.put(status, view);
            } catch(Exception e) {
            }
        }

        private boolean validate() {
            return mAdapter != null && mContext != null && mWrapper != null;
        }

        public Context getContext() {
            return mContext;
        }

        public ViewGroup getWrapper() {
            return mWrapper;
        }

        public Runnable getRetryTask() {
            return mRetryTask;
        }

        public <T> T getData() {
            try {
                return (T) mData;
            } catch(Exception e) {
            }
            return null;
        }
    }
}
