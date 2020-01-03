package com.dale.net_demo;

import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dale.constant.PermissionConstants;
import com.dale.libdemo.R;
import com.dale.net.NetSdk;
import com.dale.net.bean.DownloadRequestBuilder;
import com.dale.net.bean.NetLiveData;
import com.dale.net.callback.DownCallBack;
import com.dale.net.callback.NetObserver;
import com.dale.net.callback.OnCallBack;
import com.dale.net.exception.ErrorMessage;
import com.dale.net.utils.JsonUtils;
import com.dale.net_demo.bean.BaseEntity;
import com.dale.net_demo.bean.ListBean;
import com.dale.net_demo.bean.MyData;
import com.dale.net_demo.bean.TokenData;
import com.dale.net_demo.bean.WBaseEntity;
import com.dale.utils.PermissionUtils;
import com.dale.utils.ToastUtils;

import java.io.File;
import java.util.List;

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
        PermissionUtils.permission(PermissionConstants.STORAGE).request();



        findViewById(R.id.tv_changeBaseUrl).setOnClickListener(this);
        findViewById(R.id.tv_get).setOnClickListener(this);
        findViewById(R.id.tv_get_params).setOnClickListener(this);
        findViewById(R.id.tv_post).setOnClickListener(this);
        findViewById(R.id.tv_get_down).setOnClickListener(this);
        findViewById(R.id.tv_get_del).setOnClickListener(this);
        findViewById(R.id.tv_get_listdata).setOnClickListener(this);
        tv_pro = findViewById(R.id.tv_pro);
        progress = findViewById(R.id.progress);
        tv_result = findViewById(R.id.tv_result);
        tv_host = findViewById(R.id.tv_host);

        tv_host.setText("当前域名：" + NetSdk.getConfig().getBaseUrl());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_changeBaseUrl:
                NetSdk.setBaseUrl("https://www.soarg999.com/ZZCP/");
                tv_host.setText("当前域名：" + NetSdk.getConfig().getBaseUrl());
                break;
            case R.id.tv_get:
                getModelString();
                break;
            case R.id.tv_get_listdata:
                getListDemo();
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

        NetLiveData netLiveData = new NetLiveData<BaseEntity<MyData>>();
        netLiveData.observe(this, new NetObserver<BaseEntity<MyData>>() {
            @Override
            protected void onSuccess(BaseEntity<MyData> appConfigBean) {
                setText("ok:" + JsonUtils.toJson(appConfigBean.getData()));
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


        NetSdk.create(Api.class)
                .getModelString()
                .asLife(this)
                .send(new OnCallBack<BaseEntity<MyData>>() {
                    @Override
                    public void onSuccess(BaseEntity<MyData> appConfigBean) {
                        setText("ok:" + JsonUtils.toJson(appConfigBean));
                        Log.d("Dream", "ok:getModelString" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(ErrorMessage errorMessage) {
                        Log.d("Dream", "err:" + errorMessage.getMessage() + new Thread().getName());
                    }
                });


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


    }


    public void getListDemo() {
        Log.d("Dream", "开始 getListDemo");

        NetLiveData netLiveData = new NetLiveData<WBaseEntity<List<ListBean>>>();
        netLiveData.observe(this, new NetObserver<WBaseEntity<List<ListBean>>>() {
            @Override
            protected void onSuccess(WBaseEntity<List<ListBean>> appConfigBean) {
                setText("ok:" + JsonUtils.toJson(appConfigBean.getResult()));
                Log.d("Dream", "ok:getModelString" + Thread.currentThread().getName() +"-->" + JsonUtils.toJson(appConfigBean.getResult()));
            }

            @Override
            protected void onError(ErrorMessage errorMessage) {
                Log.d("Dream", "err:" + errorMessage.getMessage() + Thread.currentThread().getName());
            }
        });

        NetSdk.create(Api.class)
                .getListDemo()
                .asLife(this)
                .baseUrl("https://api.apiopen.top/getJoke?page=1&count=2&type=video")
                .send(netLiveData);
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


}
