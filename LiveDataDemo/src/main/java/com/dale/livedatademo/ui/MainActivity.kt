package com.dale.livedatademo.ui

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dale.framework.ui.ABBaseActivity
import com.dale.livedatademo.R
import com.dale.livedatademo.model.TestViewModel
import com.dale.livedatademo.ui.App.netLiveData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ABBaseActivity() {



    private var testViewModel: TestViewModel? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun createProvider() {
        testViewModel = ViewModelProvider(this).get(TestViewModel::class.java)
    }

    override fun initViewsAndEvents() {
        netLiveData?.observe(this,
            Observer<String> { t -> Log.d("Dream", "11->$t") })
        testViewModel?.getTestNetLiveData()

        tv_test.setOnClickListener{
            goActivity(TestJavaActivity::class.java)
        }
    }

}
