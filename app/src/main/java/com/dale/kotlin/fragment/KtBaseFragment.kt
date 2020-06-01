//package com.dale.kotlin.fragment;
//
//import android.os.Bundle
//import androidx.lifecycle.ViewModelProvider
//import com.dale.framework.ui.ABBaseFragment
//import com.ty.bwagent.bean.BaseEntity
//import com.dale.kotlin.viewmodel.TestViewModel
//import com.dale.libdemo.R
//import com.dale.net.callback.NetObserver
//import com.ty.common.ui.ABBaseFragment
//import com.ty.net.callback.NetObserver
//
//
//class KtBaseFragment : ABBaseFragment() {
//
//    private lateinit var mTestViewModel: TestViewModel
//
//    companion object {
//        fun getInstance(bundle: Bundle): KtBaseFragment {
//            val fragment = KtBaseFragment()
//            fragment.arguments = bundle
//            return fragment
//        }
//    }
//    override fun getLayoutId(): Int {
//        return R.layout.fragment_kt_base
//    }
//
//    override fun createProvider() {
//        mTestViewModel = ViewModelProvider(this).get(TestViewModel::class.java)
//        mTestViewModel.mLiveData.observe(this, object : NetObserver<BaseEntity<String>>() {
//            override fun onSuccess(t: BaseEntity<String>?) {
//
//            }
//
//            override fun onError(code: Int, errMsg: String?) {
//
//            }
//
//        })
//    }
//
//    override fun initViewsAndEvents() {
//
//    }
//
//}
//
