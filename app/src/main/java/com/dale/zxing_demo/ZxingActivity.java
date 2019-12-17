package com.dale.zxing_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dale.constant.PermissionConstants;
import com.dale.libdemo.R;
import com.dale.utils.PermissionUtils;

public class ZxingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        PermissionUtils.permission(PermissionConstants.CAMERA).request();
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZxingActivity.this,MytestActivity.class));
            }
        });
        findViewById(R.id.test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZxingActivity.this,TestGeneratectivity.class));
            }
        });
        findViewById(R.id.test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZxingActivity.this,TestScanActivity.class));
            }
        });
    }
}
