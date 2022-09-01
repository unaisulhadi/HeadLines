package com.hadi.newsapp.presentation.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hadi.newsapp.data.model.TopHeadLines
import com.hadi.newsapp.databinding.ItemTopHeadlineBinding

class TopHeadlineAdapter :
    ListAdapter<TopHeadLines.Article, TopHeadlineViewHolder>(DiffUtilCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHeadlineViewHolder {
        return TopHeadlineViewHolder(
            ItemTopHeadlineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TopHeadlineViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}


class TopHeadlineViewHolder(
    private val binding: ItemTopHeadlineBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: TopHeadLines.Article) {

        binding.tvTitle.text = data.title
        binding.ivThumbnail.load(data.urlToImage)
    }

}

class DiffUtilCallBack : DiffUtil.ItemCallback<TopHeadLines.Article>() {
    override fun areItemsTheSame(
        oldItem: TopHeadLines.Article,
        newItem: TopHeadLines.Article,
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: TopHeadLines.Article,
        newItem: TopHeadLines.Article,
    ): Boolean {
        return oldItem == newItem
    }
}