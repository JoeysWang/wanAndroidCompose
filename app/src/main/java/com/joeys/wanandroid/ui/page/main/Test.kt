package com.joeys.wanandroid.ui.page.main

import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.dp
import com.joeys.wanandroid.ui.theme.purple200
import com.joeys.wanandroid.ui.theme.purple500
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@Composable
fun Test() {
    Row {
        GameButton(true)
        GameButton(false)
    }

}

@OptIn(ObsoleteCoroutinesApi::class)
@Composable
fun GameButton(showShadow: Boolean) {
    lateinit var ticker: ReceiveChannel<Unit>
    val coroutineScope = rememberCoroutineScope()
    val size = 56.dp
    val backgroundShape = RoundedCornerShape(size / 2)
    val interactionSource = MutableInteractionSource()

    Box(
        Modifier
            .let { if (showShadow) it.shadow(10.dp, backgroundShape) else it }
            .size(size)
            .clip(backgroundShape)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        purple200,
                        purple500
                    ),
                    startY = 0f,
                    endY = 80f
                )
            )
            .indication(interactionSource = interactionSource, indication = rememberRipple())
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        coroutineScope.launch {

                        }
                        ticker = ticker(initialDelayMillis = 300, delayMillis = 60)
                        coroutineScope.launch {
                            //ticker发送连发事件
                            ticker
                                .receiveAsFlow()
                                .collect {
                                    onClick()
                                }

                        }
                    }
                }
                true
            }) {

    }

}

fun onClick() {


}
