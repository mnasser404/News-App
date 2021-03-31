package com.app.news.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.app.news.App
import com.app.news.App.Companion.getContext
import com.app.news.Utils
import com.app.news.model.AppDatabase.Companion.getDataBaseInstance
import com.app.news.model.entities.RemoteResponse.Article
import com.app.news.model.remote.ServiceGenerator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Repository {

    private lateinit var articles: LiveData<List<Article>>


    fun getNewsData(): LiveData<List<Article>> {
        if (Utils.isNetworkAvailable(App.getContext())) {
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
            getDataBaseInstance(getContext())
                .listNewsDao().delete()
            for (article in articles) {
                if (article.urlToImage == null) {
                    article.urlToImage = ""
                }
                getDataBaseInstance(getContext())
                    .listNewsDao().insert(article)
            }
            articles
        }
            .observeOn(AndroidSchedulers.mainThread())
        return LiveDataReactiveStreams.fromPublisher(response)
    }


    private fun getDataFromCache(): LiveData<List<Article>> {
        return getDataBaseInstance(getContext()).listNewsDao().getAll()
    }
}