package com.joeys.wanandroid.extend

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import com.joeys.wanandroid.network.TAG

fun String?.log() {
    if (this == null)
        Log.d(TAG, "null")
    else
        Log.d(TAG, this)
}

fun screenWidth(context: Context): Int {
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val displayMetrics = DisplayMetrics()
    wm.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}

fun String.camelToUnderLine(): String {
    return fold(StringBuilder()) { acc, c ->
        if (c.isUpperCase()) {
            acc.append("_").append(c.toLowerCase())
        } else
            acc.append(c)
    }.toString()
}


fun String.underlineToCamel(): String {
    var upperNext = false
    return fold(StringBuilder()) { acc, c ->
        if (c == '_') upperNext = true
        else acc.append(if (upperNext) c.toUpperCase() else c)
        acc
    }.toString()

}
