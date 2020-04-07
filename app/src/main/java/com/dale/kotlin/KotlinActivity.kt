package com.dale.kotlin

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.dale.framework.ui.ABBaseActivity
import com.dale.framework.ui.BasePresenter
import com.dale.kotlinlib.MyUser
import com.dale.kotlinlib.`fun`.TestFun
import com.dale.kotlinlib.lateinit.TestLateinit
import com.dale.kotlinlib.obj.TestClass
import com.dale.kotlinlib.obj.TestObject
import com.dale.libdemo.R
import com.dale.log.LogUtils
import kotlinx.android.synthetic.main.activity_kotlin.*

class KotlinActivity : ABBaseActivity<BasePresenter<*>>() ,View.OnClickListener{

    private val myUser : MyUser? = null

    override fun onClick(v: View?) {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_kotlin
    }

    override fun initViewsAndEvents() {


        LogUtils.d(myUser!!.run { toString() })

        TestObject.testObect()
        TestObject.index
        TestClass.CladObjects.p1
        TestClass.CladObjects.p2
        TestClass.CladObjects.p3 = 1
        TestClass.CladObjects.getIndex()
        TestClass.p3
        TestClass.p4 = 100
        TestClass.get2()
        btn1.setOnClickListener { }
        btn2.setOnClickListener(this)
        TestLateinit.name ="dale"
        TestLateinit.sex
        TestObject.hasCar


        btn2.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

            }
        })

        btn2.setOnClickListener{ view ->
          val id =  view.id
        }
        btn2.setOnClickListener{
            it.id
        }

        editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })


        val testFun = TestFun()
        val k = testFun.testRun()
    }


}