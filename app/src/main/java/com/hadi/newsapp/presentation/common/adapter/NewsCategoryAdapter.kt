package com.hadi.newsapp.presentation.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hadi.newsapp.databinding.ItemNewsCategoryBinding

class NewsCategoryAdapter(
    val onClick: (category: String) -> Unit,
) : RecyclerView.Adapter<NewsCategoryAdapter.CategoryViewHolder>() {

    private val list: List<String> = listOf("Sports", "Health", "Entertainment","Science", "Business", "Technology")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemNewsCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.apply {
            tvCategory.text = list[position]
            tvCategory.setOnClickListener { onClick(list[position]) }
        }
    }

    override fun getItemCount() = list.size

    class CategoryViewHolder(val binding: ItemNewsCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

}