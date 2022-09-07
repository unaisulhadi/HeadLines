package com.hadi.newsapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.hadi.newsapp.data.model.NewsResponse
import com.hadi.newsapp.data.paging.NewsPagingSource
import com.hadi.newsapp.data.remote.NewsApi
import com.hadi.newsapp.domain.repository.NewsRepository
import com.hadi.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
) : NewsRepository {

    override fun getTopHeadlines(): Flow<Resource<NewsResponse>> = flow {
        try {
            emit(Resource.Loading())
            val result = newsApi.getTopHeadLines()
            emit(Resource.Success(result))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error(
                message = "Couldn't load Headlines"
            ))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error(
                message = "Couldn't load Headlines"
            ))
        }
    }

    override fun getEverything(): Flow<Resource<NewsResponse>> = flow {
        try {
            emit(Resource.Loading())
            val result = newsApi.getEverything()
            emit(Resource.Success(result))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error(
                message = "Couldn't load News"
            ))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error(
                message = "Couldn't load News"
            ))
        }
    }

    override suspend fun getNewsByCategory(
        category: String
    ): Flow<PagingData<NewsResponse.Article>>  = Pager(
        config = PagingConfig(
            10,
            1,
            false,
        ),
        pagingSourceFactory = {
            NewsPagingSource(newsApi)
        }
    ).flow

}