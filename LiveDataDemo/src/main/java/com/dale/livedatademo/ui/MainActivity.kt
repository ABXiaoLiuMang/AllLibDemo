package com.dale.livedatademo.ui

import androidx.lifecycle.ViewModelProvider
import com.dale.framework.ui.ABBaseActivity
import com.dale.livedatademo.R
import com.dale.livedatademo.model.TestViewModel
import com.dale.net.callback.NetObserver
import com.dale.net.exception.ErrorMessage
import com.dale.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ABBaseActivity() {

    lateinit var testViewModel: TestViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun createProvider() {
        testViewModel = ViewModelProvider(this).get(TestViewModel::class.java)
    }

    override fun initViewsAndEvents() {
        testViewModel.netLiveData.observe(this, object : NetObserver<String>() {
            override fun onLoading(show: Boolean) {
                if (show) showProgressDialog() else dismissProgressDialog()
            }

            override fun onSuccess(t: String?) {
                ToastUtils.showLong(t)
            }

            override fun onError(errorMessage: ErrorMessage?) {
                ToastUtils.showLong(errorMessage?.message)
            }

        })

        tv_test.setOnClickListener{
            testViewModel.getTestNetLiveData()
        }
    }

}
