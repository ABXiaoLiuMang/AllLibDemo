package com.dale.kotlinlib.simple

import android.app.Activity
import androidx.fragment.app.Fragment
import com.dale.utils.ToastUtils

fun Activity.toast(text: String){
    ToastUtils.showLong(text)
}

fun Fragment.toast(text: String){
    ToastUtils.showLong(text)
}

fun String.lastChar(): Char = this.get(this.length - 1)