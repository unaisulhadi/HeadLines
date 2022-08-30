package com.hadi.newsapp.data.repository

import com.hadi.newsapp.data.model.TopHeadLines
import com.hadi.newsapp.data.remote.NewsApi
import com.hadi.newsapp.domain.repository.NewsRepository
import com.hadi.newsapp.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
) : NewsRepository {

    override suspend fun getAllNews(): Resource<TopHeadLines> {
        return try {
            val result = newsApi.getTopHeadLines()
            Resource.Success(result)
        }catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load Headlines"
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(
                message = "Couldn't load Headlines"
            )
        }
    }


}