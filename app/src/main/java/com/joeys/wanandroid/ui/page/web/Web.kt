package com.joeys.wanandroid.ui.page.web

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.joeys.wanandroid.extend.WebViewUtils

@Composable
fun WebPage(
    onBackPressedDispatcher: OnBackPressedDispatcher,
    title: String,
    url: String
) {
    Scaffold(
        topBar = { TopBar(onBackPressedDispatcher, title) },
        content = { WebView(url) }
    )
}


@Composable
fun TopBar(onBackPressedDispatcher: OnBackPressedDispatcher, title: String) {
    TopAppBar(
        title = {
            Text(
                text = title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.padding(end = 10.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                onBackPressedDispatcher.onBackPressed()
            }) {
                Image(
                    imageVector = Icons.Filled.ArrowBack, contentDescription = "back",
                    colorFilter = ColorFilter.tint(contentColorFor(backgroundColor = MaterialTheme.colors.onSurface))
                )
            }
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}

@Composable
fun WebView(url: String) {
    AndroidView(factory = { context ->
        android.webkit.WebView(context)
    }) {
        WebViewUtils.seWebSettings(it)
        it.loadUrl(url)
    }

}