package com.dale.net_demo;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.dale.framework_demo.MainActivity;
import com.dale.libdemo.R;
import com.dale.net.NetSdk;
import com.dale.net.bean.DownloadRequestBuilder;
import com.dale.net.bean.NetLiveData;
import com.dale.net.callback.DownCallBack;
import com.dale.net.callback.NetObserver;
import com.dale.net.callback.OnCallBack;
import com.dale.net.exception.ErrorMessage;
import com.dale.net.manager.NetConfig;
import com.dale.net.utils.NetJsonUtils;
import com.dale.net_demo.bean.MyData;
import com.dale.net_demo.bean.TokenData;
import com.dale.utils.ToastUtils;

import java.io.File;

public class NetActivity extends AppCompatActivity implements View.OnClickListener {
    String header = "";
    TextView tv_result;
    TextView tv_pro;
    TextView tv_host;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        requestPermission();

        //必须先初始化
        NetConfig defa = NetSdk.config(this)
                .baseUrl("https://www.soarg999.com/CP57/")
                .setModuleClass(Api.class)
                .needLog(true);
        NetSdk.initSdk(defa);


        findViewById(R.id.tv_changeBaseUrl).setOnClickListener(this);
        findViewById(R.id.tv_get).setOnClickListener(this);
        findViewById(R.id.tv_get_params).setOnClickListener(this);
        findViewById(R.id.tv_post).setOnClickListener(this);
        findViewById(R.id.tv_get_down).setOnClickListener(this);
        findViewById(R.id.tv_get_del).setOnClickListener(this);
        tv_pro = findViewById(R.id.tv_pro);
        progress = findViewById(R.id.progress);
        tv_result = findViewById(R.id.tv_result);
        tv_host = findViewById(R.id.tv_host);

        tv_host.setText("当前域名：" + NetSdk.getConfig(Api.class.getName()).getBaseUrl());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_changeBaseUrl:
                NetSdk.setBaseUrl("https://www.soarg999.com/ZZCP/",Api.class);
                tv_host.setText("当前域名：" + NetSdk.getConfig(Api.class.getName()).getBaseUrl());
                break;
            case R.id.tv_get:
                getModelString();
                break;
            case R.id.tv_get_params:
                if (TextUtils.isEmpty(header)) {
                    ToastUtils.showLong("请先登录");
                } else {
                    getMsgList();
                }
                break;
            case R.id.tv_post:
                getUserLoginInfo();
                break;
            case R.id.tv_get_down:
                downFile();
                break;
            case R.id.tv_get_del:
                if(downloadRequestBuilder != null){
                    downloadRequestBuilder.cancelDownload();
                }
                break;
        }
    }


    public void getModelString() {
        Log.d("Dream", "开始 getModelString");

        NetLiveData netLiveData = new NetLiveData<MyData>();
        netLiveData.observe(this, new NetObserver<MyData>() {
            @Override
            protected void onSuccess(MyData appConfigBean) {
                setText("ok:" + NetJsonUtils.toJson(appConfigBean));
                Log.d("Dream", "ok:getModelString" + Thread.currentThread().getName());
            }

            @Override
            protected void onError(ErrorMessage errorMessage) {
                Log.d("Dream", "err:" + errorMessage.getMessage() + new Thread().getName());
            }
    });

        NetSdk.create(Api.class)
                .getModelString()
                .asLife(this)
                .send(netLiveData);


//        NetSdk.create(Api.class)
//                .getModelString()
//                .asLife(this)
//                .send(new OnCallBack<MyData>() {
//                    @Override
//                    public void onSuccess(MyData appConfigBean) {
//                        setText("ok:" + NetJsonUtils.toJson(appConfigBean));
//                        Log.d("Dream", "ok:getModelString" + Thread.currentThread().getName());
//                    }
//
//                    @Override
//                    public void onError(ErrorMessage errorMessage) {
//                        Log.d("Dream", "err:" + errorMessage.getMessage() + new Thread().getName());
//                    }
//                });


//        NetSdk.create(Api.class)
//                .getModelString()
//                .addLifecycleOwner(this)
//                .send(new OnCallBack<BaseEntity<String>>() {
//                    @Override
//                    public void onSuccess(BaseEntity<String> appConfigBean) {
//                        setText("ok:" + appConfigBean.toString());
//                        Log.d("Dream", "ok:getModelString" + Thread.currentThread().getName());
//                    }
//
//                    @Override
//                    public void onError(ErrorMessage errorMessage) {
//                        Log.d("Dream", "err:" + errorMessage.getMessage() + new Thread().getName());
//                    }
//                });

    }

    public void getMsgList() {
        Log.d("Dream", "开始 getMsgList");
        NetSdk.create(Api.class)
                .getMsgList()
                .asLife(this)
                .addHeader("PLATFORM", "PLATFORM")
                .addHeader("Authorization", "Bearer " + header)
                .params("msg_type", "0")
                .params("page", "1")
                .send(new OnCallBack<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Log.d("Dream", "ok:getMsgList");
                        setText("ok:" + s);
                    }

                    @Override
                    public void onError(ErrorMessage errorMessage) {
                        Log.d("Dream", "err:" + errorMessage.getMessage());
                        setText("err:" + errorMessage.getMessage());
                    }
                });

    }

    public void getUserLoginInfo() {
        Log.d("Dream", "开始 getUserLoginInfo");

        NetLiveData netLiveData = new NetLiveData<TokenData>();
        netLiveData.observe(this, new NetObserver<TokenData>() {
            @Override
            protected void onSuccess(TokenData loginTokenEntity) {
                Log.d("Dream", "ok:getUserLoginInfo");
                header = loginTokenEntity.getToken();
                setText("ok:" + loginTokenEntity.toString());
            }

            @Override
            protected void onError(ErrorMessage errorMessage) {
                Log.d("Dream", "err:" + errorMessage.getMessage());
                setText("err:" + errorMessage.getMessage());
            }
        });

        NetSdk.create(Api.class)
                .getUserLoginInfo()
                .params("username", "div123")
                .params("password", "111111")
                .asLife(this)
                .send(netLiveData);
//        NetSdk.create(Api.class)
//                .getUserLoginInfo()
//                .params("username", "div123")
//                .params("password", "111111")
//                .asLife(this)
//                .send(new OnCallBack<TokenData>() {
//                    @Override
//                    public void onSuccess(TokenData loginTokenEntity) {
//                        Log.d("Dream", "ok:getUserLoginInfo");
//                        header = loginTokenEntity.getToken();
//                        setText("ok:" + loginTokenEntity.toString());
//                    }
//
//                    @Override
//                    public void onError(ErrorMessage errorMessage) {
//                        Log.d("Dream", "err:" + errorMessage.getMessage());
//                        setText("err:" + errorMessage.getMessage());
//                    }
//                });


    }


    DownloadRequestBuilder downloadRequestBuilder;
    private void downFile(){
        File appDir = new File(getDestPath());
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        downloadRequestBuilder = NetSdk.download()
                .url("https://imtt.dd.qq.com/16891/068AAAB2C233C9684E53DF8A13748CB9.apk?fsname=com.eg.android.AlipayGphone_10.1.55.6000_137.apk&csr=1bbd")
                .savePath(appDir.getAbsolutePath() + File.pathSeparator)
                .setModuleClass(Api.class)
                .fileName("支付宝.apk");

        downloadRequestBuilder.send(new DownCallBack() {
            @Override
            public void onProgress(Integer integer) {
                progress.setProgress(integer);
                tv_pro.setText(integer +"%");
                Log.d("Dream", "下载进度 downFile aInteger："+ integer);
            }

            @Override
            public void onSuccess(File file) {
                tv_pro.setText("下载成功");
                Log.d("Dream", "下载成功 downFile:"+ file.getPath());
            }

            @Override
            public void onError(ErrorMessage errorMessage) {
                tv_pro.setText("下载失败");
                Log.d("Dream", "下载失败 downFile:"+ errorMessage.getMessage());
            }
        });

    }

    private void setText(String text) {
        tv_result.setText(text);
    }


    private String getDestPath() {
        //sd卡是否存在
        String path = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) || !Environment.isExternalStorageRemovable()) {
            try {
                path = getExternalCacheDir().getAbsolutePath();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (TextUtils.isEmpty(path)) {
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
            }
        } else {
            path = getCacheDir().getAbsolutePath();
        }

        return path;
    }

    private static final int REQUEST_PERMISSION = 0;
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE},
                REQUEST_PERMISSION);
    }
}
