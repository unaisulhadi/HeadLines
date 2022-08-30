package com.hadi.newsapp.domain.repository

import com.hadi.newsapp.data.model.TopHeadLines
import com.hadi.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getAllNews() : Resource<TopHeadLines>

}