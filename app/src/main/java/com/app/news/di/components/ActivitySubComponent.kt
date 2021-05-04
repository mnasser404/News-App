package com.app.news.di.components

import com.app.news.di.modules.ActivityModule
import com.app.news.view.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [ActivityModule::class])
interface ActivitySubComponent {
    fun inject(mainActivity: MainActivity)
}