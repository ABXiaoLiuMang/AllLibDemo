package com.dale.kotlinlib

/**
 * 1.主构造函数至少有一个参数
 * 2.主构造函数的参数需要val或var修饰（val只有get方法，var有get和set方法）
 * 3.data 修饰的类是final修饰不可继承的
 * 4.private修饰后，不会生成get和set方法
 */
data class MyUser(val name : String, var age :Int,var eat : Eat? = null)