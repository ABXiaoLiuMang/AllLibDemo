package com.dale.kotlin

import android.app.Activity
import com.dale.kotlinlib.simple.*


fun main() {
//    GlobalScope.launch { // 在后台启动一个新的协程并继续
//        delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
//        LogUtils.d("World!") // 在延迟后打印输出
//    }
//    LogUtils.d("Hello,") // 协程已在等待时主线程还在继续
//    Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
    "".lastChar()
    var  activity :Activity? = null
    activity?.toast("--")
}