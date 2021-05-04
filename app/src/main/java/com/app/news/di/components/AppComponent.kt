package com.app.news.di.components

import com.app.news.di.modules.ActivityModule
import com.app.news.di.modules.AppModule
import com.app.news.model.Repository
import com.app.news.model.remote.ServiceGenerator
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(serviceGenerator: ServiceGenerator)
    fun inject(repository: Repository)
    fun plus(activityModule: ActivityModule) : ActivitySubComponent
}