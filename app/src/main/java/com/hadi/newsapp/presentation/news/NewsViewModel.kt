package com.hadi.newsapp.presentation.news

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hadi.newsapp.data.model.NewsResponse
import com.hadi.newsapp.domain.repository.NewsRepository
import com.hadi.newsapp.domain.usecases.UseCases
import com.hadi.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    var newsByCategoryFlow: Flow<PagingData<NewsResponse.Article>> = emptyFlow()

    fun getNewsByCategory(category: String) = viewModelScope.launch {
        newsByCategoryFlow = useCases.getNewsByCategoryUseCase(category).flow.cachedIn(viewModelScope)
    }


}
