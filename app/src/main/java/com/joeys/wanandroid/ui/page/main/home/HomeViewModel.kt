package com.joeys.wanandroid.ui.page.main.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joeys.wanandroid.data.Article
import com.joeys.wanandroid.extend.log
import com.joeys.wanandroid.network.AppModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val api by lazy { AppModule.api }

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    private val _bottomBarVisible = MutableStateFlow(true)
    val bottomBarVisible = _bottomBarVisible
        .debounce(100L)

    val isRefreshing =MutableStateFlow(true)

    private var currentPage = 0

    val loading = MutableLiveData(false)

    init {
        "HomeViewModel init".log()
        getArticle()
    }

    fun setBottomBarVisible(visible: Boolean) {
        _bottomBarVisible.tryEmit(visible)
    }

    fun getNextPage() {
        "getNextPage".log()
        getArticle(++currentPage)
    }

    fun getArticle(page: Int = 0) {
        "getArticle $page".log()
        loading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                api.getArticles(page)
            } catch (e: Exception) {
                null
            }
            if (result?.errorCode == 0) {
                currentPage = page

                val resultList = _articles.value.orEmpty().toMutableList()
                resultList.addAll(result.data.datas)
                _articles.postValue(resultList)
                "getArticle success".log()
            } else {
                "getArticle error: ${result?.errorMsg}".log()
            }
            loading.postValue(false)
        }
    }

}