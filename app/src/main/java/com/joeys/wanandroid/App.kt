package com.joeys.wanandroid

import android.app.Application
import com.joeys.wanandroid.network.AppModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppModule.prepare(this)
    }
}