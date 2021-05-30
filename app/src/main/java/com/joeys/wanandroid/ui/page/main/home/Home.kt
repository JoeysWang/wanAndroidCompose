package com.joeys.wanandroid.ui.page.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.joeys.wanandroid.R
import com.joeys.wanandroid.data.Article
import com.joeys.wanandroid.ui.page.web.WebActivity
import com.joeys.wanandroid.ui.theme.typography
import com.joeys.wanandroid.widget.SwipeRefreshLayout
import com.joeys.wanandroid.widget.isReachTheEnd
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun Home(
    homeViewModel: HomeViewModel,
    listState: LazyListState,
    navController: NavHostController
) {
    listState.isReachTheEnd {
        homeViewModel.getNextPage()
    }


    Box(Modifier.fillMaxSize()) {
        Column() {
            val list by homeViewModel.articles.observeAsState()
            val isRefreshing by homeViewModel.isRefreshing.observeAsState()
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing ?: false),
                onRefresh = {
                    homeViewModel.getArticle(0)
                }) {
                LazyColumn(
                    Modifier.fillMaxSize(),
                    state = listState
                ) {
                    itemsIndexed(list.orEmpty()) { index, article ->
                        FeedCard(navController, article)
                    }
                }
            }
        }
    }
}


@Composable
private fun FeedCard(
    navController: NavHostController,
    article: Article
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(
                    R.id.navigation_web_activity, bundleOf(
                        WebActivity.Title to article.title,
                        WebActivity.Url to article.link
                    )
                )
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
                if (!article.shareUser.isEmpty()) {
                    Text(
                        text = article.shareUser.orEmpty(),
                        style = typography.caption
                    )
                    Text(
                        text = "",
                        Modifier.padding(10.dp, 0.dp),
                        style = typography.caption
                    )
                }
                Text(
                    text = article.niceDate.orEmpty(),
                    style = typography.caption
                )
            }
        }
    }
}

