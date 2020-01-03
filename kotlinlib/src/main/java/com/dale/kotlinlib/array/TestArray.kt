package com.dale.kotlinlib.array

import com.dale.utils.LogUtils

/**
 * 1.mutable 创建的可以继续读写（添加、删除），但不能重复创建，
 * 2.listof创建的，只能读，不能写（不能添加删除），但是能重复创建
 */
open class TestArray {
    var list1 = listOf("1","2") // java list
    val list2 = mutableListOf("1","2") // java list
    var map1 = mapOf("key1" to 1,"key2" to 2)  //不可写，但可以重新创建
    var map2 = mutableMapOf("key1" to 1,"key2" to 2) //可读写
    var set1 = setOf("1")
    var set2 = mutableSetOf("1")

    //推荐这样初始化
    val map3 = mutableMapOf<String,String>().apply {
        this["key1"] = "111"
        this["key2"] = "222"
    }

    /**
     * 要创建具体类型的集合，例如 ArrayList 或 LinkedList，可以使用这些类型的构造函数。
     * 类似的构造函数对于 Set 与 Map 的各实现中均有提供。
     */
    val arrayList = ArrayList<String>(listOf("111"))

    //空集合，但是需要指明类型
    private var empty = emptyList<String>()

    /**
     * val 修饰可以添加，但是不能重新赋值
     */
    fun test(){
        list1 = listOf("4")
        list2.add("4")

        map1 = mapOf()
        map2["key3"] = 3 //可写

        for (x in list1){
            LogUtils.d("x:$x")
        }

        for (i in 0..list1.size){
            LogUtils.d("->" + list1[i])
        }

        list1.forEach {
            LogUtils.d("it:$it")
        }

       val it= list1.iterator()
        while (it.hasNext()){
            LogUtils.d(it.next())
        }

        val mit = map1.entries
        for (s  in mit){
            LogUtils.d("s:" + s.key +"  v:" + s.value)
        }

        //可变迭代器
        val it2 = list2.iterator()
        it2.remove()


    }
}