package com.hadi.newsapp.domain.usecases

data class UseCases(
    val getNewsUseCase:GetTopHeadlinesUseCase,
    val getEverythingUseCase: GetEverythingUseCase,
    val getNewsByCategoryUseCase: GetNewsByCategoryUseCase,
    val searchNewsUseCase: SearchNewsUseCase
)
