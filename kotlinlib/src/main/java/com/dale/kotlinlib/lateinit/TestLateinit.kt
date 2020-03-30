package com.dale.kotlinlib.lateinit


/**
 * 两者都可以推迟属性初始化的时间，但是lateinit var只是让编译期忽略对属性未初始化的检查，后续在哪里以及何时初始化还需要开发者自己决定。
 * 而by lazy真正做到了声明的同时也指定了延迟初始化时的行为，在属性被第一次被使用的时候能自动初始化。但这些功能是要为此付出一丢丢代价的。
 */
class TestLateinit {

    companion object{
        /**
         * 1. lateinit 初始化不赋值，只能用于var 修饰变量，因为val 不可变，
         * 2. 其次不能修饰原始数据类型（byte，char，short ,int，long，float，double）
         */
        lateinit var name :String

        /**
         * 也是延时加载，但是只能用于val(可以用于单列中，第一次加载)
         */
        val sex : String by lazy {
            "狗日的"
        }
    }

    class singletonDemo private constructor(){
        companion object{
            val instance : singletonDemo by lazy ( mode = LazyThreadSafetyMode.SYNCHRONIZED ){
                name = "haha"
                singletonDemo()
            }
        }
    }

}