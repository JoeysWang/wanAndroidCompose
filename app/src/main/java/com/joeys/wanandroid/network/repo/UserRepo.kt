package com.joeys.wanandroid.network.repo

import com.joeys.wanandroid.network.Api

class UserRepo(api: Api):IRepo(api) {

    suspend fun login(userName: String, password: String) {

    }

}