package com.hadi.newsapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hadi.newsapp.data.model.NewsResponse
import com.hadi.newsapp.data.remote.NewsApi
import com.hadi.newsapp.domain.repository.NewsRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

const val STARTING_INDEX = 1

class NewsPagingSource(
    private val newsApi: NewsApi
) : PagingSource<Int, NewsResponse.Article>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsResponse.Article> {
        val position = params.key ?: STARTING_INDEX
        return try {
            val data = newsApi.getNewsByCategory(
                category = "technology",
                page = position,
                pageSize = params.loadSize)

            LoadResult.Page(
                data = data.articles,
                prevKey = if (params.key == STARTING_INDEX) null else position - 1,
                nextKey = if (data.articles.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsResponse.Article>): Int? {
        return state.anchorPosition
    }
}