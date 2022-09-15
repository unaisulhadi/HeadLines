package com.hadi.newsapp.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingData
import com.hadi.newsapp.data.model.NewsResponse
import com.hadi.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getTopHeadlines(): Flow<Resource<NewsResponse>>

    fun getEverything(): Flow<Resource<NewsResponse>>

    suspend fun getNewsByCategory(category: String) : Flow<PagingData<NewsResponse.Article>>

}