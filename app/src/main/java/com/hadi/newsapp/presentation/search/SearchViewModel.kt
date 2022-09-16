package com.hadi.newsapp.presentation.search

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hadi.newsapp.data.model.NewsResponse
import com.hadi.newsapp.domain.repository.NewsRepository
import com.hadi.newsapp.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases,
    private val repository: NewsRepository
) : ViewModel() {

    var searchQuery: MutableLiveData<String> = MutableLiveData<String>("cats")
    var searchResults: Flow<PagingData<NewsResponse.Article>> = emptyFlow()

    val results = searchQuery.switchMap { query ->
        repository.searchNews(query)
    }

    fun searchNews(query:String) = viewModelScope.launch {
//        searchResults = useCases.searchNewsUseCase(query).cachedIn(viewModelScope)
//        searchResults = repository.searchNews(query)
    }

}
