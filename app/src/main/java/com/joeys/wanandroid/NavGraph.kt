package com.joeys.wanandroid

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.BakeryDining
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

class NavGraph {
}

sealed class Screen(
    val route: String, @StringRes val resourceId: Int,
    val iconSelected: ImageVector,
    val iconUnSelected: ImageVector
) {
    object Home : Screen("Home", R.string.home, Icons.Filled.Home, Icons.Outlined.Home)
    object Test : Screen("Test", R.string.test, Icons.Filled.BakeryDining, Icons.Outlined.BakeryDining)
    object Profile : Screen("Profile", R.string.profile, Icons.Filled.Person, Icons.Outlined.Person)
}
