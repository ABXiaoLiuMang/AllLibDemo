package com.dale.kotlinlib.obj


/**
 * 1.在class中创建静态内部内也用object修饰
 * 2.var 变量（可以读写）
 * 3.val 常量（只读）
 */

class TestClass{

    var tc = 3 //这样修饰的外面都访问不到

    //相当于静态内部类
    object CladObjects{
        const val p1 = 8 //const 相对于public修饰的静态常量（不能修改）
        val p2 = 8       //相当于 private修饰的静态常量，但是会自动生成一个静态方法供获取（不推荐此方法）
        var p3 = 10

        fun getIndex() : Int{
            return 0
        }
    }

    //伴生对象，少了类名字（相当于静态代码块）
    companion object{
        fun get2():String{
            return "hahah"
        }
        const val  p3 = 9 // val修饰 常量，不能修改
        var  p4 = 9       //var 不是常量，可以修改（也就是相当于生成了静态的get 和set方法）
    }


}