package com.dale.framework.view.state;

import android.content.Context;
import android.view.View;

public interface IStateView {

    View getView(Context context);

    void hide();

    void show();
}
