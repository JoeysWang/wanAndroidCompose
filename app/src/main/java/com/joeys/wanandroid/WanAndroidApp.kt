package com.joeys.wanandroid

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.navigation.activity
import androidx.navigation.compose.*
import com.joeys.wanandroid.ui.page.home.Home
import com.joeys.wanandroid.ui.page.Profile
import com.joeys.wanandroid.ui.page.web.WebActivity

@Composable
fun WanAndroidApp(backPressedDispatcher: OnBackPressedDispatcher) {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Home,
        Screen.Profile,
    )
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.surface
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                items.forEach { screen ->
                    val selected = currentRoute == screen.route
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
                                    if (selected) MaterialTheme.colors.primary
                                    else
                                        contentColorFor(
                                            backgroundColor = MaterialTheme.colors.onSurface
                                        )
                                )
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(screen.resourceId),
                                color = if (selected) MaterialTheme.colors.primary
                                else
                                    contentColorFor(
                                        backgroundColor = MaterialTheme.colors.onSurface
                                    )
                            )
                        },

                        )
                }
            }
        },
        topBar = {
            TopAppBar(
                title = { Text(text = "Home") },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            imageVector = Icons.Filled.Menu, contentDescription = "menu",
                            colorFilter = ColorFilter.tint(
                                contentColorFor(backgroundColor = MaterialTheme.colors.onSurface)
                            )
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.surface
            )
        }
    ) {

        NavHost(navController = navController, startDestination = Screen.Home.route) {

            composable(Screen.Home.route) { Home(navController) }
            composable(Screen.Profile.route) { Profile(navController) }
            activity(R.id.navigation_web_activity){
                activityClass = WebActivity::class
            }
        }
    }

}