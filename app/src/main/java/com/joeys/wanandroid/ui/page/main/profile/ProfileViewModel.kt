package com.joeys.wanandroid.ui.page.main.profile

import androidx.lifecycle.ViewModel
import com.joeys.wanandroid.network.AppModule
import com.joeys.wanandroid.network.repo.UserRepo

class ProfileViewModel : ViewModel() {

    val userRepo: UserRepo by lazy { AppModule.userRepo }



}