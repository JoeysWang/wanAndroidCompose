package com.joeys.wanandroid.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.joeys.wanandroid.extend.log

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeRefreshLayout(
    modifier: Modifier = Modifier,
    scrollState: SwipeRefreshLayoutState = SwipeRefreshLayoutState(),
    onRefreshListener: () -> Unit = {},
    content: @Composable () -> Unit
) {
    var headerOffset by remember { mutableStateOf(0f) }
    var headerHeight by remember { mutableStateOf(0f) }

    Layout(
        modifier = modifier.draggable(
            orientation = Orientation.Vertical,
            onDragStarted = {

            },
            onDragStopped = { velocity ->

            },
            state = rememberDraggableState { dy ->
                "dy $dy".log()
                headerOffset += dy
                if (headerOffset > headerHeight) {
                    headerOffset = headerHeight
                }
                if (headerOffset < 0) {
                    headerOffset = 0f
                }
                scrollState.headerOffset = headerOffset
            },
        ),
        content = {
            Box(
                Modifier
                    .height(56.dp)
                    .fillMaxWidth()
                    .background(Color.Red)
            ) {
                Text(text = "${headerOffset}",Modifier.align(  Alignment .Center))
            }
            content()
        },
        measurePolicy = { measurables: List<Measurable>,
                          constraints: Constraints ->
            "constraints width=${constraints.maxWidth} height=${constraints.maxHeight}".log()

            //1.测量组件们
            val placeables = measurables.mapIndexed { index, placeable ->
                val result = placeable.measure(constraints)
                if (index == 0) {
                    headerHeight = result.height.toFloat()
                }
                result
            }


            //2.摆放被测量的组件
            layout(constraints.maxWidth, constraints.maxHeight) {
                var y = 0
                placeables.forEachIndexed { index, placeable ->
                    if (index == 0) {
                        placeable.place(x = 0, y = (-headerHeight +headerOffset).toInt())
                    }
                    if (index > 0) {
                        //"placeable width=${placeable.width} height=${placeable.height}".log()
                        placeable.placeRelative(x = 0, y = y)
                        y += placeable.height
                    }
                }
            }
        })
}

data class SwipeRefreshLayoutState(
    var headerOffset: Float = 0f
) {
    var maxScrollOffset: Float = 60f
    val minScrollOffset: Float = 0f


}