package com.app.news.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.news.R
import com.app.news.model.entities.RemoteResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_layout.view.*

class NewsAdapter(var context: Context, var newsList: List<RemoteResponse.Article>) :
    RecyclerView.Adapter<NewsAdapter.NewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): NewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_layout, viewGroup, false)
        val holder = NewHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewHolder, position: Int) {
        val currentNew = newsList.get(position)
        holder.newsHeadline.text = currentNew.title
        Glide.with(context).load(currentNew.urlToImage).into(holder.newsImage)
    }


    inner class NewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var newsImage: ImageView
        var newsHeadline: TextView


        init {
            newsImage = itemView.newsImage
            newsHeadline = itemView.newsHeadLine
        }


    }


}
