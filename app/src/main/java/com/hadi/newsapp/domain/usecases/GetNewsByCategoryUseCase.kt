package com.hadi.newsapp.domain.usecases

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingData
import com.hadi.newsapp.data.model.NewsResponse
import com.hadi.newsapp.domain.repository.NewsRepository
import com.hadi.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsByCategoryUseCase @Inject constructor(
    private val repository: NewsRepository,
) {

    suspend operator fun invoke(category: String) : Pager<Int, NewsResponse.Article> {
        return repository.getNewsByCategory(category)
    }

}