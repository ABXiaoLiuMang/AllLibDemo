package com.dale.livedatademo.navigation.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dale.livedatademo.R

class KotlinMainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_main)
    }


//    override fun onSupportNavigateUp() =
//        Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()
}