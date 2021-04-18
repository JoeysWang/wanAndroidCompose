package com.joeys.wanandroid.extend

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.Window
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
fun setStatusBarLightMode(
    window: Window,
    isLightMode: Boolean
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val decorView: View = window.decorView
        var vis: Int = decorView.systemUiVisibility
        vis = if (isLightMode) {
            vis or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            vis and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
        decorView.systemUiVisibility = vis
    }
}