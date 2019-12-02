package com.app.news.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class RemoteResponse(val articles: List<Article>, val status: String, val totalResults: Int) {

    @Entity(tableName = "list_news")
    data class Article(
        @ColumnInfo(name = "news_headline") @PrimaryKey var title: String,
        @ColumnInfo(name = "news_image") var urlToImage: String
    )

    data class Source(
        val id: Any,
        val name: String
    )
}