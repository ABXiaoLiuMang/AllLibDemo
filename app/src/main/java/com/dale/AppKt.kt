package com.dale

import com.dale.framework.util.ABApplication
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader

class AppKt :ABApplication() {
    companion object{
       init {
           SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
               ClassicsHeader(context)
           }


       }
    }
}