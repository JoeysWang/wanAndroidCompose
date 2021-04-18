package com.joeys.wanandroid.ui.page.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.google.accompanist.coil.CoilImage
import com.joeys.wanandroid.ui.theme.typography
import com.joeys.wanandroid.widget.SwipeRefreshLayout
import com.joeys.wanandroid.widget.SwipeRefreshLayoutState
import kotlin.math.roundToInt

@Composable
fun Profile(navController: NavHostController) {
    val userViewModel: UserViewModel = viewModel()

    Column(
        Modifier
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Join WanAndroid", style = typography.h4,modifier = Modifier.padding(top = 26.dp,bottom = 26.dp)
        )

        OutlinedTextField(value = userViewModel.userName.collectAsState().value, onValueChange = {
            userViewModel.userName.value = it
        }, label = { Text(text = "用户名") })

        OutlinedTextField(
            value = userViewModel.passWord.collectAsState().value,
            onValueChange = {
                userViewModel.passWord.value = it
            },
            label = { Text(text = "密码") })
        Spacer(modifier = Modifier.height(26.dp))
        Button(
            onClick = { },
            enabled = userViewModel.loginEnable.value,
            modifier = Modifier.width(100.dp)
        ) {
            Text(text = "登录")
        }
    }
}


fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = Modifier.layout { measurable, constraints ->

    //1.只能测量一次，
    // measurable:被测量的组件
    // constraints:宽高约束
    val placeable = measurable.measure(constraints)
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]

    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
    val height = placeable.height + placeableY


    //2.摆放被测量的组件
    layout(placeable.width, height) {
        placeable.placeRelative(0, placeableY)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeableSample() {
    val width = 96.dp
    val squareSize = 48.dp

    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states

    Box(
        modifier = Modifier
            .width(width)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .background(Color.LightGray)
    ) {
        Box(
            Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .size(squareSize)
                .background(Color.DarkGray)
        )
    }
}