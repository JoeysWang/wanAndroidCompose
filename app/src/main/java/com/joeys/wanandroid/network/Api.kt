package com.joeys.wanandroid.network

import com.joeys.wanandroid.data.ArticleResult
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("article/list/{page}/json")
    suspend fun articles(@Path("page") page: Int):ArticleResult

}