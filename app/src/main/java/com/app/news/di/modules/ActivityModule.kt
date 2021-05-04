package com.app.news.di.modules

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.news.view.NewsAdapter
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    fun getLayoutManager(context: Context): LinearLayoutManager{
        return LinearLayoutManager(context)
    }

    @Provides
    fun getNewsAdapter(context: Context, requestManager: RequestManager) : NewsAdapter{
        return NewsAdapter(context, requestManager)
    }

}