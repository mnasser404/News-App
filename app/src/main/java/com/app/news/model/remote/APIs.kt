package com.app.news.model.remote

import com.app.news.model.entities.RemoteResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface APIs {
    @GET(NetworkConstants.TOP_HEAD_LINE_URL)
    fun getNewsList(@Query("country") country: String, @Query("apiKey") apiKey: String): Flowable<RemoteResponse>
}