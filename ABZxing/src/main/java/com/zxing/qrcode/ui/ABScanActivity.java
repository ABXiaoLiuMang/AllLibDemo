package com.zxing.qrcode.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.dale.constant.PermissionConstants;
import com.dale.utils.PermissionUtils;
import com.dale.utils.StringUtils;
import com.dale.utils.ToastUtils;
import com.zxing.qrcode.R;
import com.zxing.qrcode.core.QRCodeView;
import com.zxing.qrcode.view.ZXingView;

/**
 * create by Dale
 * create on 2019/7/24
 * description: 扫描基类
 */
public abstract class ABScanActivity extends AppCompatActivity implements QRCodeView.Delegate {
    private static final int OPEN_PHOTO = 10086;// 打开相册
    protected ZXingView mZXingView;

    protected abstract void scanSuccess(String result);

    protected abstract void scanError(String result);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mZXingView = findViewById(R.id.zXingView);
        mZXingView.setDelegate(this);
    }

    protected int getLayoutId() {
        return R.layout.activity_default_scan;
    }

    /**
     * 打开相册
     */
    protected void openPhoto() {
        PermissionUtils.permission(PermissionConstants.STORAGE)
                .callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {
                        Intent innerIntent = new Intent();
                        if (Build.VERSION.SDK_INT < 19) {
                            innerIntent.setAction(Intent.ACTION_GET_CONTENT);
                        } else {
                            innerIntent.setAction(Intent.ACTION_OPEN_DOCUMENT);
                        }
                        innerIntent.setType("image/*");
                        Intent wrapperIntent = Intent.createChooser(innerIntent, "选择二维码图片");
                        startActivityForResult(wrapperIntent, OPEN_PHOTO);
                    }

                    @Override
                    public void onDenied() {
                        ToastUtils.showLong("权限不足");
                    }
                })
                .request();


    }

    /**
     * 打开灯
     */
    protected void openFlashlight() {
        mZXingView.openFlashlight();
    }

    /**
     * 关闭灯
     */
    protected void closeFlashlight() {
        mZXingView.closeFlashlight();
    }

    @Override
    protected void onStart() {
        super.onStart();
        PermissionUtils.permission(PermissionConstants.CAMERA)
                .callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {
                        mZXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
                        mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
                    }

                    @Override
                    public void onDenied() {
                        ToastUtils.showLong("权限不足");
                    }
                })
                .request();
    }

    @Override
    protected void onStop() {
        mZXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mZXingView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case OPEN_PHOTO:
                    String[] proj = {MediaStore.Images.Media.DATA};
                    // 获取选中图片的路径
                    Cursor cursor = getContentResolver().query(data.getData(),
                            proj, null, null, null);
                    if (cursor.moveToFirst()) {
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        String photo_path = cursor.getString(column_index);
                        if (photo_path == null) {
                            photo_path = getPath(getApplicationContext(),
                                    data.getData());
                        }
                        if (!StringUtils.isEmpty(photo_path)) {
                            mZXingView.decodeQRCode(photo_path);
                        } else {
                            ToastUtils.showLong("未知错误");
                        }
                    }
                    cursor.close();
            }
        }
    }


    @Override
    public void onScanQRCodeSuccess(String result) {
        vibrate();
        mZXingView.startSpot();
        scanSuccess(result);
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
        // 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
        String tipText = mZXingView.getScanBoxView().getTipText();
        String ambientBrightnessTip = "\n环境过暗，请打开闪光灯";
        if (isDark) {
            if (!tipText.contains(ambientBrightnessTip)) {
                mZXingView.getScanBoxView().setTipText(tipText + ambientBrightnessTip);
            }
        } else {
            if (tipText.contains(ambientBrightnessTip)) {
                tipText = tipText.substring(0, tipText.indexOf(ambientBrightnessTip));
                mZXingView.getScanBoxView().setTipText(tipText);
            }
        }
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        scanError("打开相机出错");
    }

    private String getPath(final Context context, final Uri uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String docId = DocumentsContract.getDocumentId(uri);
            String[] split = docId.split(":");
            Uri contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            String selection = "_id=?";
            String[] selectionArgs = new String[]{split[1]};
            return getDataColumn(context, contentUri, selection,
                    selectionArgs);
        }
        return null;
    }


    private String getDataColumn(Context context, Uri uri,
                                 String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection,
                    selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
}
