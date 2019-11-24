package com.dale.location_demo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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

    }

}
