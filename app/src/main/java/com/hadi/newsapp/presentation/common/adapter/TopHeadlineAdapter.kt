package com.hadi.newsapp.presentation.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hadi.newsapp.data.model.NewsResponse
import com.hadi.newsapp.databinding.ItemTopHeadlineBinding
import com.hadi.newsapp.utils.setMinSaturation

class TopHeadlineAdapter(
    val onClick : (article : NewsResponse.Article) -> Unit
) :
    ListAdapter<NewsResponse.Article, TopHeadlineViewHolder>(DiffUtilCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHeadlineViewHolder {
        return TopHeadlineViewHolder(
            ItemTopHeadlineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TopHeadlineViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        holder.binding.itemRoot.setOnClickListener {
            onClick(item)
        }
    }
}


class TopHeadlineViewHolder(
    val binding: ItemTopHeadlineBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: NewsResponse.Article) {

        binding.tvTitle.text = data.title
        binding.ivThumbnail.load(data.urlToImage)
        binding.ivThumbnail.setMinSaturation()
    }

}

