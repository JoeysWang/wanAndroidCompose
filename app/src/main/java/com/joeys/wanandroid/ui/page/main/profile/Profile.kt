package com.joeys.wanandroid.ui.page.main.profile

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusOrder
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.joeys.wanandroid.ui.theme.typography
import kotlin.math.roundToInt

@Composable
fun Profile(userViewModel: UserViewModel, navController: NavHostController) {
    val focusRequester = remember {
        FocusRequester()
    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 18.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "加入玩Android",
            fontSize = 34.sp,
            letterSpacing = 0.25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 26.dp, bottom = 26.dp)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            value = userViewModel.userName.observeAsState().value.orEmpty(),
            onValueChange = {
                userViewModel.userName.value = it
            },
            label = { Text(text = "用户名") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                focusRequester.requestFocus()
            })
        )

        OutlinedTextField(
            value = userViewModel.passWord.observeAsState().value.orEmpty(),
            onValueChange = {
                userViewModel.passWord.value = it
            },
            label = { Text(text = "密码") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                userViewModel.login()
            })
        )
        Spacer(modifier = Modifier.height(26.dp))
        OutlinedButton(
            onClick = userViewModel::login,
            modifier = Modifier
                .padding(top = 20.dp, bottom = 24.dp)
                .fillMaxWidth()
        ) {
            Text(text = "登录")
        }
    }
}
//
//@Composable
//fun Modifier.firstBaselineToTop(
//    firstBaselineToTop: Dp
//) = Modifier.layout { measurable, constraints ->
//
//    //1.只能测量一次，
//    // measurable:被测量的组件
//    // constraints:宽高约束
//    val placeable = measurable.measure(constraints)
//    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
//    val firstBaseline = placeable[FirstBaseline]
//
//    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
//    val height = placeable.height + placeableY
//
//
//    //2.摆放被测量的组件
//    layout(placeable.width, height) {
//        placeable.placeRelative(0, placeableY)
//    }
//}

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