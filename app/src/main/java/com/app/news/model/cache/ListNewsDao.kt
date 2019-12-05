package com.app.news.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.news.model.entities.RemoteResponse

@Dao
interface ListNewsDao {

    @Query("SELECT * FROM list_news")
    fun getAll(): LiveData<List<RemoteResponse.Article>>

    @Insert
    fun insert(article: RemoteResponse.Article)

    @Query("DELETE FROM list_news")
    fun delete()

}