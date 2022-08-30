package com.hadi.newsapp.data.repository

import com.hadi.newsapp.data.model.TopHeadLines
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

    override fun getTopHeadlines(): Flow<Resource<TopHeadLines>> = flow{
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


}