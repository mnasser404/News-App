package com.app.news.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.news.model.entities.RemoteResponse


@Database(entities = arrayOf(RemoteResponse.Article::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun listNewsDao(): ListNewsDao

}