package com.hadi.newsapp.presentation.common.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hadi.newsapp.data.model.NewsResponse
import com.hadi.newsapp.databinding.ItemNewsBinding
import com.hadi.newsapp.utils.setMinSaturation

class NewsListViewHolder(
    val binding: ItemNewsBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: NewsResponse.Article) {

        binding.tvTitle.text = data.title
        binding.tvContent.text = data.content
        //
        binding.ivThumbnail.setMinSaturation()
        binding.ivThumbnail.load(data.urlToImage)

    }

}