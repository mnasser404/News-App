package com.app.news.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.app.news.App
import com.app.news.Utils
import com.app.news.model.entities.RemoteResponse.Article
import com.app.news.model.remote.ServiceGenerator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository {

    private lateinit var articles: LiveData<List<Article>>

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var database: AppDatabase

    init {
        App.getAppComponent().inject(this)
    }


    fun getNewsData(): LiveData<List<Article>> {
        if (Utils.isNetworkAvailable(context)) {
            articles = getDataFromRemote()
        } else {
            articles = getDataFromCache()
        }
        return articles
    }

    private fun getDataFromRemote(): LiveData<List<Article>> {
        val newsService = ServiceGenerator()
        val response = newsService.callNewsApiRequest().subscribeOn(
            Schedulers.io()
        ).map<List<Article>> { (articles) ->
            database.listNewsDao().delete()
            for (article in articles) {
                if (article.urlToImage == null) {
                    article.urlToImage = ""
                }
                database.listNewsDao().insert(article)
            }
            articles
        }
            .observeOn(AndroidSchedulers.mainThread())
        return LiveDataReactiveStreams.fromPublisher(response)
    }


    private fun getDataFromCache(): LiveData<List<Article>> {
        return database.listNewsDao().getAll()
    }
}