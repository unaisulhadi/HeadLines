package com.hadi.newsapp.presentation.home

import androidx.lifecycle.*
import com.hadi.newsapp.data.model.TopHeadLines
import com.hadi.newsapp.domain.repository.NewsRepository
import com.hadi.newsapp.domain.usecases.UseCases
import com.hadi.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: UseCases,
    private val repository: NewsRepository
) : ViewModel() {

    private var _headlines = MutableLiveData<Resource<TopHeadLines>>()
    var headlines : LiveData<Resource<TopHeadLines>>  = _headlines

    init {
        getTopHeadLines()
    }

    private fun getTopHeadLines() = viewModelScope.launch {

        useCase.getNewsUseCase().collect{
            _headlines.postValue(it)
        }

    }


}
