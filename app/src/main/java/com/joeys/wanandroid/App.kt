package com.joeys.wanandroid

import android.app.Application
import com.joeys.wanandroid.network.AppModule

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        context = this
        AppModule.prepare(this)
    }

    companion object {

        lateinit var context: App
    }
}