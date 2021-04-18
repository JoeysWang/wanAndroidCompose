package com.joeys.wanandroid.ui.page.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joeys.wanandroid.data.Article
import com.joeys.wanandroid.extend.log
import com.joeys.wanandroid.network.AppModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val api by lazy { AppModule.api }

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

    val loading = MutableLiveData(false)

    init {
        getArticle()
    }

    fun getArticle() {
        "getArticle".log()
        loading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val result = try {
                api.getArticles(0)
            } catch (e: Exception) {
                null
            }
            if (result?.errorCode == 0) {
                _articles.postValue(result.data.datas)
                "getArticle success".log()
            } else {
                "getArticle error: ${result?.errorMsg}".log()
            }
            loading.postValue(false)
        }
    }

}