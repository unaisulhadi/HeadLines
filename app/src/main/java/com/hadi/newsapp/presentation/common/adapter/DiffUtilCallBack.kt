package com.hadi.newsapp.presentation.common.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hadi.newsapp.data.model.NewsResponse

class DiffUtilCallBack : DiffUtil.ItemCallback<NewsResponse.Article>() {
    override fun areItemsTheSame(
        oldItem: NewsResponse.Article,
        newItem: NewsResponse.Article,
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: NewsResponse.Article,
        newItem: NewsResponse.Article,
    ): Boolean {
        return oldItem == newItem
    }
}