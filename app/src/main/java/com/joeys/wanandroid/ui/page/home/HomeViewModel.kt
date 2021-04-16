package com.joeys.wanandroid.ui.page.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joeys.wanandroid.data.Article
import com.joeys.wanandroid.extend.log
import com.joeys.wanandroid.network.AppModule
import com.joeys.wanandroid.network.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val api by lazy { AppModule.api }

    private val _artilces = MutableLiveData<List<Article>>()
    val artilces: LiveData<List<Article>> = _artilces


    fun getArticle() {
        "getArticle".log()
        viewModelScope.launch(Dispatchers.IO) {
            val result = api.articles(0)
            if (result.errorCode == 0) {
                _artilces.postValue(result.data?.datas.orEmpty())
                "getArticle success".log()
            } else {
                "getArticle error: ${result.errorMsg}".log()
            }
        }
    }

}