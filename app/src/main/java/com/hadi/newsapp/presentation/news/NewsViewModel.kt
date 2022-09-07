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
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val useCases: UseCases,
    private val repository: NewsRepository
) : ViewModel() {

    var newsByCategory: LiveData<PagingData<NewsResponse.Article>> = MutableLiveData()


    var newsByCategoryFlow : Flow<PagingData<NewsResponse.Article>> = emptyFlow()

    fun getNewsByCategory(category: String) = viewModelScope.launch {
        newsByCategoryFlow = repository.getNewsByCategory(category).cachedIn(viewModelScope)
        //newsByCategoryFlow = useCases.getNewsByCategoryUseCase(category)
    }




}
