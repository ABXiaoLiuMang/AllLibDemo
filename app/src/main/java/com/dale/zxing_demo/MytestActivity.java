package com.dale.zxing_demo;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.dale.constant.PermissionConstants;
import com.dale.utils.PermissionUtils;
import com.dale.utils.ToastUtils;
import com.zxing.qrcode.ui.ABScanActivity;

public class MytestActivity extends ABScanActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionUtils.permission(PermissionConstants.CAMERA).request();
    }

    @Override
    protected void scanSuccess(String result) {
        ToastUtils.showLong("成功:" + result);
    }

    @Override
    protected void scanError(String result) {
        ToastUtils.showLong("失败:" + result);
    }
}
