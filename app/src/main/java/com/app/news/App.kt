package com.app.news

import android.app.Application
import android.content.Context
import com.app.news.model.remote.NetworkConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    companion object {

        private lateinit var appContext: Context
        private var retrofit: Retrofit? = null
        private var okHttpClient: OkHttpClient? = null

        fun getContext(): Context {
            return appContext
        }

        fun getRetrofitInstance(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(NetworkConstants.BASE_URL)
                    .client(getOkHttpClientInstance())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun getOkHttpClientInstance(): OkHttpClient? {
            if (okHttpClient == null) {
                okHttpClient = OkHttpClient()
                    .newBuilder()
                    .build()
            }
            return okHttpClient
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

}