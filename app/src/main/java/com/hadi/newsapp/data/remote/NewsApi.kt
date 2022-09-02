package com.hadi.newsapp.data.remote

import com.hadi.newsapp.BuildConfig
import com.hadi.newsapp.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    companion object{
        const val BASE_URL = "https://newsapi.org/v2/"
    }

    @GET("top-headlines")
    suspend fun getTopHeadLines(
        @Query("country") country:String = "in",
        @Query("apiKey") apiKey:String = BuildConfig.API_KEY
    ): NewsResponse


    @GET("everything?q=technology")
    suspend fun getEverything(
        @Query("apiKey") apiKey:String = BuildConfig.API_KEY
    ): NewsResponse




}