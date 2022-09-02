package com.hadi.newsapp.domain.repository

import com.hadi.newsapp.data.model.NewsResponse
import com.hadi.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getTopHeadlines() : Flow<Resource<NewsResponse>>

    fun getEverything()  : Flow<Resource<NewsResponse>>

}