package com.joeys.wanandroid.ui.page.main

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.activity
import androidx.navigation.compose.*
import com.joeys.wanandroid.R
import com.joeys.wanandroid.Screen
import com.joeys.wanandroid.ui.page.main.home.Home
import com.joeys.wanandroid.ui.page.main.home.HomeViewModel
import com.joeys.wanandroid.ui.page.main.profile.Profile
import com.joeys.wanandroid.ui.page.main.profile.UserViewModel
import com.joeys.wanandroid.ui.page.web.WebActivity
import com.joeys.wanandroid.widget.TopBar
import com.joeys.wanandroid.widget.isScrollingUp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@Composable
fun MainPage() {

    val navController = rememberNavController()
    val homePages = listOf(
        Screen.Home,
        Screen.Profile,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var currentScreen: Screen = Screen.Home
    val listState = rememberLazyListState()
    val homeViewModel: HomeViewModel = viewModel()

    homePages.forEach { screen ->
        if (currentRoute == screen.route) {
            currentScreen = screen
        }
    }

    val bottomBarVisible = homeViewModel.bottomBarVisible.collectAsState(initial = true)
    homeViewModel.setBottomBarVisible(
        listState.isScrollingUp()
    )

    Scaffold(
        bottomBar = BottomBar(
            bottomBarVisible.value,
            navController,
            homePages,
            currentScreen
        ),
        topBar = {
            TopBar(stringResource(id = currentScreen.resourceId))
        },
        content = {
            Content(
                listState,
                navController
            )
        })
}

@Composable
private fun Content(lazyListState: LazyListState, navController: NavHostController) {
    val homeViewModel: HomeViewModel = viewModel()
    val userViewModel: UserViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { Home(homeViewModel, lazyListState, navController) }
        composable(Screen.Profile.route) { Profile(userViewModel, navController) }
        activity(R.id.navigation_web_activity) {
            activityClass = WebActivity::class
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun BottomBar(
    visible: Boolean,
    navController: NavHostController,
    homePages: List<Screen>,
    currentScreen: Screen
) =
    @Composable {
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(durationMillis = 150)
            ),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(durationMillis = 150)
            )
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
                                popUpTo = navController.graph.startDestinationId
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