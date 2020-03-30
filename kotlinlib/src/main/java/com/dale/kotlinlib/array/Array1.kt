package com.dale.kotlinlib.array

class Array1 {

    //静态初始化
   private val arr = arrayOf("1","2")

    //动态初始化
    private val arr2 = arrayOfNulls<String>(4)

    fun test(){
        arr[1] = "ss"
        arr2[1] ="sss"
        val  c = arr.asList()
    }
}