package com.joeys.wanandroid.widget

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun TopBar(
    title: String,
    navIcon: ImageVector? = null,
    onNavIconClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.padding(end = 10.dp),
                color = Color.White
            )
        },
        Modifier
            .background(Color.Black)
            .statusBarsPadding(),
        navigationIcon = if (navIcon != null) Icon(navIcon, onNavIconClick) else null,
        backgroundColor = Color.Black
    )
}

@Composable
private fun Icon(
    navIcon: ImageVector,
    onNavIconClick: (() -> Unit)?
) = @Composable {
    IconButton(onClick = {
        onNavIconClick?.invoke()
    }) {
        Image(
            imageVector = navIcon, contentDescription = "navIcon",
            colorFilter = ColorFilter.tint(Color.White)
        )
    }
}

