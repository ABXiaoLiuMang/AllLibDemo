package com.dale.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import com.dale.framework.R;
import com.lxj.xpopup.widget.LoadingView;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class StateLayout extends FrameLayout {
    private final static int STATE_LOADING = 0x10;
    private final static int STATE_EMPTY = 0x11;
    private final static int STATE_NET_ERROR = 0x12;


    @IntDef({STATE_LOADING, STATE_EMPTY, STATE_NET_ERROR,
            StateLayout.VISIBLE, StateLayout.INVISIBLE, StateLayout.GONE})
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {

    }

    private int mLayoutId;
    private int mResIdEmpty, mResIdNetError;

    private LinearLayout x_state_layout;
    private ImageView x_state_iv;
    private TextView x_state_tv;
    private Button x_state_btn;
    private LoadingView x_state_loading;

    public StateLayout(Context context) {
        this(context, null);
    }

    public StateLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StateLayout);
        mLayoutId = typedArray.getResourceId(R.styleable.StateLayout_state_layout, R.layout.x_state_layout);
        mResIdEmpty = typedArray.getResourceId(R.styleable.StateLayout_state_emptyDrawable, R.mipmap.x_state_empty);
        mResIdNetError = typedArray.getResourceId(R.styleable.StateLayout_state_netErroDrawable, R.mipmap.x_state_no_wifi);
        typedArray.recycle();
        init(context);
    }

    protected void init(Context context) {
        View root = LayoutInflater.from(context).inflate(mLayoutId, this);
        x_state_layout = root.findViewById(R.id.x_state_layout);
        x_state_iv = root.findViewById(R.id.x_state_iv);
        x_state_tv = root.findViewById(R.id.x_state_tv);
        x_state_btn = root.findViewById(R.id.x_state_btn);
        x_state_loading = root.findViewById(R.id.x_state_loading);
    }

    private void showLoading() {
        setVisibility(VISIBLE);
        x_state_loading.setVisibility(VISIBLE);
        x_state_layout.setVisibility(GONE);
    }

    private void showEmpty() {
        setVisibility(VISIBLE);
        x_state_loading.setVisibility(GONE);
        x_state_layout.setVisibility(VISIBLE);
        x_state_iv.setImageResource(mResIdEmpty);
        x_state_tv.setText(getResources().getString(R.string.state_no_data));
        x_state_btn.setVisibility(GONE);
    }

    private void showNetError() {
        setVisibility(VISIBLE);
        x_state_loading.setVisibility(GONE);
        x_state_layout.setVisibility(VISIBLE);
        x_state_iv.setImageResource(mResIdNetError);
        x_state_tv.setText(getResources().getString(R.string.state_net_error));
        x_state_btn.setText(getResources().getString(R.string.state_retry));
        x_state_btn.setVisibility(VISIBLE);
    }

    public StateLayout icon(@DrawableRes int resId) {
        x_state_iv.setImageResource(resId);
        return this;
    }



    public StateLayout desc(CharSequence text) {
        x_state_tv.setText(text);
        return this;
    }


    public StateLayout button(CharSequence text, int visibility) {
        x_state_btn.setText(text);
        x_state_btn.setVisibility(visibility);
        return this;
    }


    public StateLayout setState(@State int state) {
        switch (state) {
            case GONE:
            case VISIBLE:
            case INVISIBLE:
                setVisibility(state);
                break;
            case STATE_LOADING:
                showLoading();
                break;
            case STATE_EMPTY:
                showEmpty();
                break;
            case STATE_NET_ERROR:
                showNetError();
                break;
        }
        return this;
    }
}
