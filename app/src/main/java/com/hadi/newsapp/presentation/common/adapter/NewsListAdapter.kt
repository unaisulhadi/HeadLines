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

class NewsListAdapter :
    ListAdapter<NewsResponse.Article, NewsListViewHolder>(DiffUtilCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        return NewsListViewHolder(
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}


class NewsListViewHolder(
    private val binding: ItemNewsBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: NewsResponse.Article) {

        binding.tvTitle.text = data.title
        binding.tvContent.text = data.content
        //
        binding.ivThumbnail.setMinSaturation()
        binding.ivThumbnail.load(data.urlToImage)
    }

}
