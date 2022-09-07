package com.hadi.newsapp.presentation.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.hadi.newsapp.data.model.NewsResponse
import com.hadi.newsapp.databinding.ItemNewsBinding

class NewsPagingAdapter :
    PagingDataAdapter<NewsResponse.Article, NewsListViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        return NewsListViewHolder(
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}