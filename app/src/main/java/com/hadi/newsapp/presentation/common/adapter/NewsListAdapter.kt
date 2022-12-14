package com.hadi.newsapp.presentation.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hadi.newsapp.data.model.NewsResponse
import com.hadi.newsapp.databinding.ItemNewsBinding
import com.hadi.newsapp.databinding.ItemTopHeadlineBinding
import com.hadi.newsapp.utils.setMinSaturation

class NewsListAdapter(
    val onClick : (article : NewsResponse.Article) -> Unit
) :
    ListAdapter<NewsResponse.Article, NewsListViewHolder>(DiffUtilCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        return NewsListViewHolder(
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.root.setOnClickListener {
            onClick(item)
        }
    }
}



