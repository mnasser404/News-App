package com.app.news.di.modules

import android.content.Context
import androidx.room.Room
import com.app.news.model.AppDatabase
import com.app.news.model.remote.NetworkConstants
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(private var context: Context) {

    @Provides
    fun getContext(): Context{
        return context
    }

    @Provides
    fun getGlideRequestManager(context: Context): RequestManager{
        return Glide.with(context)
    }

    @Provides
    fun getServerBaseUrl(): String{
        return NetworkConstants.BASE_URL
    }

    @Provides
    fun getOkHttpClient(): OkHttpClient{
        return OkHttpClient().newBuilder().build()
    }

    @Provides
    fun getRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory{
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    fun getGsonConverterFactory(): GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Provides
    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(getServerBaseUrl())
            .client(getOkHttpClient())
            .addCallAdapterFactory(getRxJava2CallAdapterFactory())
            .addConverterFactory(getGsonConverterFactory())
            .build()
    }

    @Provides
    fun getDataBaseInstance(context: Context): AppDatabase{
        return Room.databaseBuilder(context, AppDatabase::class.java, "news-db").build()
    }


}