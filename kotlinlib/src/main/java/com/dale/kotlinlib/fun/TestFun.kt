package com.dale.kotlinlib.`fun`

import com.dale.kotlinlib.Eat
import com.dale.kotlinlib.MyUser
import com.dale.utils.LogUtils

class TestFun {

    /**
     * let函数
     * 实际是函数的一个作用域，需要一个变量在一个作用域内使用，如adapter的item绑定
     * 常用于针对一个对象null的判断
     */
    fun testLet(){
        val myUser = MyUser("dale",10000)

        myUser.let {
            LogUtils.d("-->" + it.age + it.name)
        }

        //myUser不为null才执行
        myUser!!.let {
            LogUtils.d("-->" + it.age + it.name)
        }

    }

    /**
     * with 函数，第一个参数接收一个对象，第二个参数接收一个函数
     * 在函数中可以用this替代传递的第一个参数，这样可以直接在函数中直接调用函数的方法和属性
     * 可以没有返回值
     */
    fun testWith() : Int{
        val myUser = MyUser("dale",10000)
        myUser.eat = Eat("香蕉")

        with(myUser.eat){
            LogUtils.d(this?.food)
            this?.testPrint()

            LogUtils.d(myUser.eat?.food) //可以简写
        }
        return 1
    }

    /**
     * 相当于let 和with的结合体
     * 1.解决在let中用it参数替代对象
     * 2.解决with传递参数的null处理
     */
    fun testRun() :Int{
        val myUser = MyUser("dale",10000)
        myUser.eat = Eat("香蕉")

        myUser.eat?.run {
            LogUtils.d(this.food)
            this.testPrint()
        }
        return 1
    }
}