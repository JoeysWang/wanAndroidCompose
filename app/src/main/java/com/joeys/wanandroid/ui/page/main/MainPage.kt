package com.joeys.wanandroid.ui.page.main

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.activity
import androidx.navigation.compose.*
import com.joeys.wanandroid.R
import com.joeys.wanandroid.Screen
import com.joeys.wanandroid.ui.page.main.home.Home
import com.joeys.wanandroid.ui.page.main.home.isScrollingUp
import com.joeys.wanandroid.ui.page.main.profile.Profile
import com.joeys.wanandroid.ui.page.web.WebActivity
import kotlinx.coroutines.delay

@Composable
fun MainPage() {
    val navController = rememberNavController()
    val homePages = listOf(
        Screen.Home,
        Screen.Profile,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
    var currentScreen: Screen = Screen.Home
    val lazyListState = rememberLazyListState()

    homePages.forEach { screen ->
        if (currentRoute == screen.route) {
            currentScreen = screen
        }
    }

    var bottomBarVisible by remember { mutableStateOf(true) }
    bottomBarVisible =
        if (lazyListState.isScrollingUp() != bottomBarVisible) lazyListState.isScrollingUp() else bottomBarVisible

    Scaffold(
        bottomBar = {
            BottomBar(
                bottomBarVisible,
                navController,
                homePages,
                currentScreen
            )
        },
        topBar = { TopBar(currentScreen) },
        content = {
            Content(
                lazyListState,
                navController
            )
        })
}

@Composable
private fun Content(lazyListState: LazyListState, navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { Home(lazyListState, navController) }
        composable(Screen.Profile.route) { Profile(navController) }
        activity(R.id.navigation_web_activity) {
            activityClass = WebActivity::class
        }
    }
}

@Composable
private fun TopBar(currentScreen: Screen) {
    TopAppBar(
        title = { Text(text = stringResource(id = currentScreen.resourceId)) },
        backgroundColor = MaterialTheme.colors.surface
    )
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun BottomBar(
    visible: Boolean,
    navController: NavHostController,
    homePages: List<Screen>,
    currentScreen: Screen
) {

    AnimatedVisibility(
        visible = visible,
//        I have no idea why these didn't work
//        enter = slideInVertically(
//            initialOffsetY = { fullHeight -> fullHeight },
//            animationSpec = tween(durationMillis = 150, )
//        ),
//        exit = slideOutVertically(
//            targetOffsetY = { fullHeight -> fullHeight },
//            animationSpec = tween(durationMillis = 250,)
//        )
    ) {
        BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {
            homePages.forEach { screen ->
                val selected = currentScreen.route == screen.route
                val color by animateColorAsState(
                    if (selected) MaterialTheme.colors.primary
                    else contentColorFor(backgroundColor = MaterialTheme.colors.onSurface)
                )

                BottomNavigationItem(
                    selected = selected,
                    onClick = {
                        navController.navigate(screen.route) {
                            launchSingleTop = true
                            popUpTo = navController.graph.startDestination
                        }
                    },
                    icon = {
                        Image(
                            imageVector = if (selected) screen.iconSelected else screen.iconUnSelected,
                            contentDescription = "icon",
                            colorFilter = ColorFilter.tint(
                                color
                            )
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(screen.resourceId),
                            color = color
                        )
                    })
            }
        }
    }

}