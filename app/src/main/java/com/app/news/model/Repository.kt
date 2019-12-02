package com.app.news.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.app.news.App.Companion.getContext
import com.app.news.model.entities.RemoteResponse.Article
import com.app.news.model.remote.ServiceGenerator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nasser.com.athletes.data.cache.AppDatabase.Companion.getDataBaseInstance

class Repository {

    fun getDataFromRemote(): LiveData<List<Article>> {
        val newsService = ServiceGenerator()
        val response = newsService.callNewsApiRequest().subscribeOn(
            Schedulers.io()
        ).map<List<Article>>({ (articles) ->
            getDataBaseInstance(getContext())
                .listAtheletsDao().delete()
            for (article in articles) {
                if (article.urlToImage == null) {
                    article.urlToImage = ""
                }
                getDataBaseInstance(getContext())
                    .listAtheletsDao().insert(article)
            }
            articles
        })
            .observeOn(AndroidSchedulers.mainThread())
        return LiveDataReactiveStreams.fromPublisher(response)
    }


    fun getDataFromCache(): LiveData<List<Article>> {
        return getDataBaseInstance(getContext()).listAtheletsDao().getAll()
    }
}