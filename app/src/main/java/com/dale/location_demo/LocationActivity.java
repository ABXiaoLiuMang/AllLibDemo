package com.dale.location_demo;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.dale.libdemo.R;
import com.dale.location.LocationSdk;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        findViewById(R.id.test).setOnClickListener(view ->  {
            LocationSdk.ins().startLocation();
        });

        ImageView wechat_receiver_imgage = findViewById(R.id.imgage);

        Glide.with(this).load("/storage/emulated/0/DCIM/camera/IMG_20191210_135852.jpg").into(wechat_receiver_imgage);
    }

}
