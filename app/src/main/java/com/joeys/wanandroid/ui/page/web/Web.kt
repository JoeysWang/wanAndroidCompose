package com.joeys.wanandroid.ui.page.web

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import com.joeys.wanandroid.ui.theme.typography

@Composable
fun Web(title: String) {
    Surface() {

        Text(
            text = "web-->$title",
        )
    }

}