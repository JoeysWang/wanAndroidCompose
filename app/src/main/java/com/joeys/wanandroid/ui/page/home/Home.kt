package com.joeys.wanandroid.ui.page.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.joeys.wanandroid.R
import com.joeys.wanandroid.data.Article
import com.joeys.wanandroid.ui.theme.typography

@Composable
fun Home(navController: NavHostController) {
    val homeViewModel: HomeViewModel = viewModel()
    homeViewModel.getArticle()

    Box(Modifier.fillMaxSize()) {
        Column() {
            val list by homeViewModel.artilces.observeAsState()
            LazyColumn(Modifier.fillMaxSize()) {
                itemsIndexed(list.orEmpty()) { index, article ->
                    Surface(
                        elevation = 2.dp,
                        color = MaterialTheme.colors.surface
                    ) {
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(R.id.navigation_web_activity, bundleOf("title" to article.title))
                                }
                                .padding(12.dp, 16.dp)
                        ) {
                            Text(
                                text = article.title.orEmpty(),
                                fontSize = 16.sp,
                                style = typography.h5
                            )
                            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp)
                                ) {
                                    Text(
                                        text = article.shareUser.orEmpty(),
                                        style = typography.caption
                                    )
                                    Text(
                                        text = "",
                                        Modifier.padding(10.dp, 0.dp),
                                        style = typography.caption
                                    )
                                    Text(
                                        text = article.niceDate.orEmpty(),
                                        style = typography.caption
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}