package com.hadi.newsapp.di

import com.hadi.newsapp.data.remote.NewsApi
import com.hadi.newsapp.data.repository.NewsRepositoryImpl
import com.hadi.newsapp.domain.repository.NewsRepository
import com.hadi.newsapp.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(newsApi: NewsApi) : NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: NewsRepository) : UseCases  {
        return UseCases(
            getNewsUseCase = GetTopHeadlinesUseCase(repository),
            getEverythingUseCase = GetEverythingUseCase(repository),
            getNewsByCategoryUseCase = GetNewsByCategoryUseCase(repository),
            searchNewsUseCase = SearchNewsUseCase(repository)
        )
    }

}