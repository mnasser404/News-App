package com.app.news.model.remote

import com.app.news.App
import com.app.news.model.entities.RemoteResponse
import io.reactivex.Flowable

class ServiceGenerator {
    fun callNewsApiRequest(): Flowable<RemoteResponse> {
        return App.getRetrofitInstance()!!.create(APIs::class.java)
            .getNewsList(NetworkConstants.UNITED_STATES, NetworkConstants.API_KEY)
    }
}