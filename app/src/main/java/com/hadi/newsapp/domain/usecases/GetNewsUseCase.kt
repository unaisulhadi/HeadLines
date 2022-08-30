package com.hadi.newsapp.domain.usecases

import com.hadi.newsapp.data.model.TopHeadLines
import com.hadi.newsapp.domain.repository.NewsRepository
import com.hadi.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository,
) {

    operator fun invoke(): Flow<Resource<TopHeadLines>> = repository.getTopHeadlines()


}