package com.joeys.wanandroid.widget

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@Composable
fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

@Composable
fun LazyListState.isReachTheEnd(
    onTheEnd: () -> Unit
) {
    LaunchedEffect(this) {
        snapshotFlow { layoutInfo.visibleItemsInfo.lastOrNull() }
            .map { last ->
                if (last != null)
                    last.index == layoutInfo.totalItemsCount - 1
                else
                    false
            }
            .distinctUntilChanged()
            .collect {
                if (it) onTheEnd.invoke()
            }
    }
}