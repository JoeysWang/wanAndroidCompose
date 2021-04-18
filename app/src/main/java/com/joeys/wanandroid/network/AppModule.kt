package com.joeys.wanandroid.network

import android.app.Application
import com.joeys.wanandroid.network.repo.ArticleRepo
import com.joeys.wanandroid.network.repo.UserRepo
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const val TAG = "wanAndroid"

object AppModule {

    lateinit var retrofit: Retrofit
    lateinit var api: Api
    lateinit var userRepo: UserRepo
    lateinit var articleRepo: ArticleRepo

    fun prepare(application: Application) {
        retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .client(okhttp())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
        userRepo = UserRepo(api)
        articleRepo = ArticleRepo(api)
    }

    fun okhttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(SaveCookieInterceptor())
            .build()
    }

}

class SaveCookieInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val requestUrl = request.url().toString()
        val domain = request.url().host()
        // set-cookie maybe has multi, login to save cookie
        if ((requestUrl.contains(HttpConstant.SAVE_USER_LOGIN_KEY)
                    || requestUrl.contains(HttpConstant.SAVE_USER_REGISTER_KEY))
            && !response.headers(HttpConstant.SET_COOKIE_KEY).isEmpty()
        ) {
            val cookies = response.headers(HttpConstant.SET_COOKIE_KEY)
            val cookie = HttpConstant.encodeCookie(cookies)
            HttpConstant.saveCookie(requestUrl, domain, cookie)
        }
        return response
    }
}