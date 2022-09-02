package com.hadi.newsapp.domain.usecases

import com.hadi.newsapp.data.model.NewsResponse
import com.hadi.newsapp.domain.repository.NewsRepository
import com.hadi.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEverythingUseCase @Inject constructor(
    private val repository: NewsRepository,
) {

    operator fun invoke(): Flow<Resource<NewsResponse>>  {
        return repository.getEverything()
    }

}