package com.dale.kotlinlib

//https://blog.csdn.net/u012721519/article/details/79536989 在线视频地址
//https://blog.csdn.net/u013064109/article/details/80387322 (kotlin学校)

/**
 * 1.主构造函数至少有一个参数
 * 2.主构造函数的参数需要val或var修饰（val只有get方法，var有get和set方法）
 * 3.data 修饰的类是final修饰不可继承的
 * 4.private修饰后，不会生成get和set方法
 */
data class MyUser(val name : String, var age :Int,var eat : Eat? = null){
    override fun toString(): String {
        return "MyUser(name='$name', age=$age, eat=$eat)"
    }
}