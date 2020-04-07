package com.dale.kotlinlib.obj

/**
 *  1.object修饰的类为静态的，里面的方法和变量都是静态的，注意区别于class修身
 *  2.
 */
object TestObject {
    var index = 2

    fun testObect(){

    }

    val hasCar :Boolean
        get(){
        return index == -1
    }
}