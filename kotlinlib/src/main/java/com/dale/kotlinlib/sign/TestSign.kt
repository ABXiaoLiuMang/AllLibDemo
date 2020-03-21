package com.dale.kotlinlib.sign

import android.app.Activity

class TestSign {
    /**
     *  ? 操作符
     */
    var name :String?  ="张三"//表示name 可为空

    /**
     * ?: 操作符
     * 1.如果name为空，获取不到长度，就用后面-1的值
     */
   private val l = name?.length ?: -1

    /**
     * !! 操作符
     * 1.如果name为空，就会抛出空指针
     */
    private val b = name!!.length


    /**
     *  == 和 === 操作符
     *  1.前者判断值是否相等，后者判断是不是完全相等
     */
    val d1 = l == b
    val d2 = l === b

    /**
     * 倒序循环
     */
    fun downto(){
        for (i in 4 downTo 1){
            //4321
        }
    }

    /**
     * :: 操作符
     * 得到类的class对象
     */
    val cls = TestSign::class.java

    /**
     * @ 符合
     * 1.限定this的类型
     */
    fun getThis():TestSign{
        return this@TestSign
//        return this
    }

    /**
     * as? 操作符
     * 1、强制转换操作符，转换不成功返回null
     */
    val mas = name as? Int

    /**
     * is 操作符
     * 1.检查某个实例是否是某个类型，如果判断出属于某个类型，那么判断后的分支中可以直接可当该类型使用，无需显示转换
     */
    fun testIs(obj : Any){
        if(obj is Activity){
            obj.finish()
        }
    }

    /**
     * 多行输入符 三个双引号
     */
    val sh = """go
        to
        so
    """

    /**
     * $操作符
     * 模板替换（打印）
     */
    val bian:String ="tttt $name"
}