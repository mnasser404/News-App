package com.app.news.model.remote

import com.app.news.App
import com.app.news.model.entities.RemoteResponse
import io.reactivex.Flowable
import retrofit2.Retrofit
import javax.inject.Inject

class ServiceGenerator {

    @Inject
    lateinit var retrofit: Retrofit

    init {
        App.getAppComponent().inject(this)
    }


    fun callNewsApiRequest(): Flowable<RemoteResponse> {
        return retrofit!!.create(APIs::class.java)
            .getNewsList(NetworkConstants.UNITED_STATES, NetworkConstants.API_KEY)
    }
}