package com.joeys.wanandroid.network

import android.app.Application
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val TAG="wanAndroid"
object AppModule {

    lateinit var retrofit: Retrofit
    lateinit var api: Api

    fun prepare(application: Application) {
        retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
    }

}