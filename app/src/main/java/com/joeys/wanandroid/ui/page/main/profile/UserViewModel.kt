package com.joeys.wanandroid.ui.page.main.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    val userName = MutableStateFlow("")
    val passWord = MutableStateFlow("")
    val loginEnable = MutableStateFlow(false)


    init {
        viewModelScope.launch {
            userName.combine(passWord) { name, pwd ->
                name.length > 3 && pwd.length > 3
            }.collect {
                loginEnable.value = it
            }
        }
    }

}