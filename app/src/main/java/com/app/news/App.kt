package com.app.news

import android.app.Application
import com.app.news.di.components.AppComponent
import com.app.news.di.modules.AppModule
import com.app.news.di.components.DaggerAppComponent

class App : Application() {

    companion object{
        private lateinit var appComponent: AppComponent

        fun getAppComponent() : AppComponent {
            return appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(applicationContext)).build()
    }



}