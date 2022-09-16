package com.hadi.newsapp.domain.usecases

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.hadi.newsapp.data.model.NewsResponse
import com.hadi.newsapp.domain.repository.NewsRepository
import com.hadi.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val repository: NewsRepository,
) {

    operator fun invoke(query: String): LiveData<PagingData<NewsResponse.Article>> {
        return repository.searchNews(query)
    }

}