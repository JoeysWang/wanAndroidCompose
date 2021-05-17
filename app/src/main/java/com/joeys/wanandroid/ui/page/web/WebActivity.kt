package com.joeys.wanandroid.ui.page.web

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.joeys.wanandroid.ui.theme.MyTheme

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title = intent?.extras?.getString(Title) ?: "noTitle"
        val url = intent?.extras?.getString(Url) ?: "noUrl"
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MyTheme {
                ProvideWindowInsets(consumeWindowInsets = false) {
                    WebPage(onBackPressedDispatcher, title, url)
                }
            }
        }
    }

    companion object {
        const val Title = "title"
        const val Url = "url"
    }
}