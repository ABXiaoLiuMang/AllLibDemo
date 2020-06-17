package com.dale.xweb.webview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.dale.constant.LibApplication;
import com.dale.constant.PermissionConstants;
import com.dale.utils.PermissionUtils;
import com.dale.utils.ToastUtils;
import com.dale.utils.TopActivityManager;
import com.just.agentweb.WebChromeClient;

import java.io.File;
import java.io.IOException;

/**
 * WebChromeClient的默认实现 支持图片选择
 */

public class XWebChromeDefaultClient extends WebChromeClient {
    private ValueCallback<Uri[]> uploadMessageAboveL;

    private ValueCallback<Uri> uploadMessage;


    private final static int FILE_CHOOSER_RESULT_CODE = 10000;
    /**
     * 相机request code
     */
    private static final int REQ_CAMERA = FILE_CHOOSER_RESULT_CODE + 2;

    /**
     * 图片路径
     */
    private String imagePaths;

    /**
     * 相机拍照uri
     */
    private Uri cameraUri;

    /**
     * 图片路径
     */
    private String compressPath = "";

    private Context mContext;

    public XWebChromeDefaultClient() {
        mContext = LibApplication.getApp();
    }

    // For Android 3.0+
    public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
        if (uploadMessage != null) return;
        uploadMessage = uploadMsg;
        selectImage();
    }

    // For Android < 3.0
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        openFileChooser(uploadMsg, "");
    }

    // For Android  > 4.1.1
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        openFileChooser(uploadMsg, acceptType);
    }

    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        uploadMessageAboveL = filePathCallback;
        selectImage();
        return true;
    }

    /**
     * 选择图片
     * + "/fuiou_wmp/temp"
     */
    public final void selectImage() {
        if (!checkSdCard())
            return;
        String[] selectPicTypeStr = {"相机", "图库"};
        Activity activity =  TopActivityManager.getInstance().getCurActivity();
        new AlertDialog.Builder(activity)
                .setItems(selectPicTypeStr,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    // 相机拍摄
                                    case 0:
                                        obtainCameraPermission();
                                        break;
                                    // 手机相册
                                    case 1:
                                        obtainStoragePermission();
                                        break;
                                    default:
                                        break;
                                }
                                compressPath = Environment.getExternalStorageDirectory().getPath();
                                new File(compressPath).mkdirs();
                                compressPath = compressPath + File.separator + "compress.jpg";
                            }
                        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                cancelCallback();
            }
        }).show();
    }


    /**
     * 获取相机权限
     */
    private void obtainCameraPermission() {
        PermissionUtils.permission(PermissionConstants.STORAGE, PermissionConstants.CAMERA)
                .callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {
                        openCamera();
                    }

                    @Override
                    public void onDenied() {
                        ToastUtils.showLong("请打开存储和相机权限");
                    }
                }).request();
    }

    /**
     * 获取存储权限
     */
    private void obtainStoragePermission() {
        PermissionUtils.permission(PermissionConstants.STORAGE)
                .callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {
                        openImageChooserActivity();
                    }

                    @Override
                    public void onDenied() {
                        ToastUtils.showLong("请打开存储权限");
                    }
                }).request();
    }

    /**
     * 跳转系统图库界面
     */
    private void openImageChooserActivity() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        Activity activity = TopActivityManager.getInstance().getCurActivity();
        if (activity != null) {
            activity.startActivityForResult(Intent.createChooser(i, "Image Chooser"), FILE_CHOOSER_RESULT_CODE);
        }
    }

    /**
     * 打开照相机
     * + "/fuiou_wmp/temp/"
     */
    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imagePaths = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + "obaoImage";
        File file = new File(imagePaths);
        if (!file.exists()) {
            file.mkdirs();
        }
        String imageName = System.currentTimeMillis() + ".jpg";
        File imageFile;
        try {
            imageFile = File.createTempFile(imageName, ".jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            String authority = LibApplication.getApp().getPackageName() + ".utilcode.provider";
            cameraUri = FileProvider.getUriForFile(mContext, authority, imageFile);
        } else {
            cameraUri = Uri.fromFile(imageFile);
        }

        intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        Activity activity = TopActivityManager.getInstance().getCurActivity();
        if (activity != null) {
            activity.startActivityForResult(intent, REQ_CAMERA);
        }
    }

    /**
     * 检查SD卡是否存在
     *
     * @return
     */
    public final boolean checkSdCard() {
        boolean flag = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (!flag) {
            ToastUtils.showToast("请插入手机存储卡再使用本功能！");
        }
        return flag;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (null == uploadMessage && null == uploadMessageAboveL) {
            return;
        }

        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            Uri result = data == null || resultCode != Activity.RESULT_OK ? null : data.getData();
            if (uploadMessageAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (uploadMessage != null) {
                uploadMessage.onReceiveValue(result);
                uploadMessage = null;
            }
        } else if (requestCode == REQ_CAMERA) {
            if (resultCode == -1) {
                afterOpenCamera();
                if (uploadMessageAboveL != null) {
                    chooseAbove(resultCode, data);
                } else if (uploadMessage != null) {
                    chooseBelow(resultCode, data);
                }
            }
        }
        cancelCallback();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent intent) {
        if (requestCode != FILE_CHOOSER_RESULT_CODE || uploadMessageAboveL == null) {
            return;
        }

        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (intent != null) {
                String dataString = intent.getDataString();
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }
                if (dataString != null) {
                    results = new Uri[]{Uri.parse(dataString)};
                }

            }
        }
        if (uploadMessageAboveL != null) {
            uploadMessageAboveL.onReceiveValue(results);
        }
        uploadMessageAboveL = null;

    }

    /**
     * 防止点击dialog的取消按钮之后，就不再次响应点击事件了
     */
    public void cancelCallback() {
        try {
            if (uploadMessageAboveL != null) {
                uploadMessageAboveL.onReceiveValue(null);
            }

            if (uploadMessage != null) {
                uploadMessage.onReceiveValue(null);
            }
        } catch (Exception e) {
        }
    }

    /**
     * Android API < 21(Android 5.0)版本的回调处理
     *
     * @param resultCode 选取文件或拍照的返回码
     * @param data       选取文件或拍照的返回结果
     */
    private void chooseBelow(int resultCode, Intent data) {
        if (null != uploadMessage) {
            if (Activity.RESULT_OK == resultCode) {
                updatePhotos();

                if (data != null) {
                    // 这里是针对文件路径处理
                    Uri uri = data.getData();
                    if (uri != null) {
                        uploadMessage.onReceiveValue(uri);
                    } else {
                        uploadMessage.onReceiveValue(null);
                    }
                } else {
                    // 以指定图像存储路径的方式调起相机，成功后返回data为空
                    uploadMessage.onReceiveValue(cameraUri);
                }
            } else {
                uploadMessage.onReceiveValue(null);
            }
        }
        uploadMessage = null;
    }


    /**
     * Android API >= 21(Android 5.0) 版本的回调处理
     *
     * @param resultCode 选取文件或拍照的返回码
     * @param data       选取文件或拍照的返回结果
     */
    private void chooseAbove(int resultCode, Intent data) {
        if (null != uploadMessageAboveL) {
            if (Activity.RESULT_OK == resultCode) {
                updatePhotos();

                if (data != null) {
                    // 这里是针对从文件中选图片的处理
                    Uri[] results;
                    Uri uriData = data.getData();
                    if (uriData != null) {
                        results = new Uri[]{uriData};
                        uploadMessageAboveL.onReceiveValue(results);
                    } else {
                        uploadMessageAboveL.onReceiveValue(null);
                    }
                } else {
                    uploadMessageAboveL.onReceiveValue(new Uri[]{cameraUri});
                }
            } else {
                uploadMessageAboveL.onReceiveValue(null);
            }
        }
        uploadMessageAboveL = null;
    }

    /**
     * 拍照结束后
     */
    private void afterOpenCamera() {
        File f = new File(imagePaths);
        addImageGallery(f);
    }

    /**
     * 解决拍照后在相册中找不到的问题
     */
    private void addImageGallery(File file) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        mContext.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }

    /**
     * 发送广播刷新系统图库
     */
    private void updatePhotos() {
        // 该广播即使多发（即选取照片成功时也发送）也没有关系，只是唤醒系统刷新媒体文件
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(cameraUri);
        mContext.sendBroadcast(intent);
    }
}
