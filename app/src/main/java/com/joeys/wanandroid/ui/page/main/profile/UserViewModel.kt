package com.joeys.wanandroid.ui.page.main.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.joeys.wanandroid.extend.log
import com.joeys.wanandroid.network.AppModule
import com.joeys.wanandroid.network.repo.UserRepo
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    val userName = MutableLiveData("")
    val passWord = MutableLiveData("")
    val loginEnable = MutableLiveData(false)
    val userRepo: UserRepo by lazy { AppModule.userRepo }


    init {
        "UserViewModel init".log()
        viewModelScope.launch {

//
//            userName.combine(passWord) { name, pwd ->
//                name.length > 3 && pwd.length > 3
//            }.collect {
//                loginEnable.value = it
//            }
        }
    }

    fun login() {
        "UserViewModel login ${userName.value}  ${passWord.value}".log()

    }

    override fun onCleared() {
        super.onCleared()
        "UserViewModel onCleared".log()
    }
}