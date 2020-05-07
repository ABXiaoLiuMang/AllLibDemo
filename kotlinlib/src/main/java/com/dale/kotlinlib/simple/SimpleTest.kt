package com.dale.kotlinlib.simple

import com.dale.kotlinlib.Model
import com.dale.kotlinlib.MyUser
import com.dale.utils.StringUtils

//https://www.jianshu.com/p/f5f0d38e3e44 (DSL 高阶函数讲解)

class SimpleTest {

    fun funadd(x: Int,y:Int) :Int{
        return x + y
    }

    fun funaddSimple(x: Int,y:Int) :Int = x + y

    fun testInit(){
        var m1 = Model()
        m1.age = 10
        m1.nams="kaka"

        var simpleM1 = Model().apply {
            this.age = 10
            this.nams = "kak"
        }

        var simplem2 = Model().let {
            it.age = 10
            it.nams ="kaka"
        }

        var list1 = ArrayList<String>()
        list1.add("1")
        list1.add("2")

        var simplelist1 = mutableListOf("1","2")

        //集合操作符
//                find	返回满足条件的成员
//                first	返回第一个满足条件的成员
//                last	返回最后一个满足条件的成员
//                indexOf	返回成员变量所在的索引
//                take	返回满足条件的子集合
//                filter	返回根据条件过滤出来的子集合
//                sort	根据排序条件，对当前集合进行排序
//                all	当集合中的所有成员都满足条件时，返回true
//                any	如果集合中至少有一个成员满足条件，则返回true
//                sum	返回集合中所有成员相加的和

        list1.find {
            StringUtils.equalsIgnoreCase(it,"kak")
        }
        simplelist1.find {
            StringUtils.equalsIgnoreCase(it,"kak")
        }

        val myUser:MyUser = MyUser("kk",18,null)
        val simplemyUser : MyUser by lazy {
            MyUser("dd",12,null)
        }


    }

    fun printSum(sum:(Int,Int)->Int){

    }
    val sum = {x:Int,y:Int ->x + y}

    fun r(){
        printSum(sum)
    }
}