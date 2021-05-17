package com.joeys.wanandroid.ui.page.web

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.viewinterop.AndroidView
import com.joeys.wanandroid.extend.WebViewUtils
import com.joeys.wanandroid.widget.TopBar
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import android.R

import android.webkit.WebView

import android.webkit.WebChromeClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier


@Composable
fun WebPage(
    onBackPressedDispatcher: OnBackPressedDispatcher,
    title: String,
    url: String
) {
    Scaffold(
        topBar = {
            TopBar(title, Icons.Filled.ArrowBack) {
                onBackPressedDispatcher.onBackPressed()
            }
        },
        content = { WebContent(url) }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WebContent(url: String) {
    var progress by remember { mutableStateOf(0f) }
    Box {
        WebView(url) {
            progress = it
        }
        AnimatedVisibility(visible = progress <= 0.9f,exit = fadeOut()) {
            LinearProgressIndicator(progress, Modifier.fillMaxWidth())
        }
    }
}


@Composable
fun WebView(url: String, onProgressChange: (Float) -> Unit) {
    AndroidView(factory = { context ->
        android.webkit.WebView(context)
    }) {
        WebViewUtils.seWebSettings(it)
        it.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                onProgressChange(progress / 100f)
            }
        }
        it.loadUrl(url)
    }
}