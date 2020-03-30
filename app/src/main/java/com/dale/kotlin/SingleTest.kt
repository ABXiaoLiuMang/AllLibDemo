package com.dale.kotlin

class SingleTest private constructor(){
    companion object{
        val instant : SingleTest by lazy (mode = LazyThreadSafetyMode.SYNCHRONIZED){ SingleTest() }
    }
}