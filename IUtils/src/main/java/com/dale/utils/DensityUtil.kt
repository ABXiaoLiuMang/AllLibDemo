package com.dale.utils

import android.content.Context
import android.util.TypedValue

fun Context.dp2px(dp: Float): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp,
    this.resources.displayMetrics
)

fun Context.sp2dp(sp: Float): Float = sp / (this.resources.displayMetrics.density)

fun Context.sp2px(sp: Float): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_SP,
    sp,
    this.resources.displayMetrics
)

fun Context.px2sp(px: Float): Float = px / this.resources.displayMetrics.scaledDensity