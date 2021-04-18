package com.joeys.wanandroid.extend

import android.os.Build
import android.webkit.WebView

import android.webkit.WebViewClient

import android.webkit.WebSettings


object WebViewUtils {
    fun seWebSettings(webView: WebView) {
        val webseting = webView.settings
        webseting.setAppCacheMaxSize((1024 * 1024 * 8).toLong())
        webseting.defaultTextEncodingName = "UTF-8"
        webseting.allowFileAccess = true
        webseting.setAppCacheEnabled(true)
        webseting.allowContentAccess = true
        webseting.builtInZoomControls = false
        //自适应屏幕
        webseting.cacheMode = WebSettings.LOAD_DEFAULT
        webseting.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        webseting.setRenderPriority(WebSettings.RenderPriority.HIGH);//提高渲染的优先级

        // 开启 DOM storage API 功能
        webseting.domStorageEnabled = true
        webseting.javaScriptEnabled = true
        webseting.setSupportZoom(true)
        webseting.useWideViewPort = true
        webseting.loadWithOverviewMode = true
        webseting.blockNetworkImage = false //把图片加载放在最后来加载渲染
        webseting.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
    }

}