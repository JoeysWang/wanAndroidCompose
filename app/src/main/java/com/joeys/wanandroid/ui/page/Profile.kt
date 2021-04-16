package com.joeys.wanandroid.ui.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate

@Composable
fun Profile(navController: NavHostController) {
    Box(Modifier.fillMaxSize()) {
        Column() {
            Text(text = "Profile page")
            Button(onClick = {
                navController.navigate("Home")
            }) {
                Text(text = "To Home")
            }
        }
    }
}

