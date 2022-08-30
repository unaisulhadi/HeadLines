package com.hadi.newsapp.presentation.home

import androidx.lifecycle.*
import com.hadi.newsapp.data.model.TopHeadLines
import com.hadi.newsapp.domain.repository.NewsRepository
import com.hadi.newsapp.domain.usecases.UseCases
import com.hadi.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: UseCases,
    private val repository: NewsRepository
) : ViewModel() {

    var text = "TEXT"
    private var _headlines = MutableLiveData<Resource<TopHeadLines>>()
    var headlines : LiveData<Resource<TopHeadLines>>  = _headlines

    init {
        getTopHeadLines()
    }

    private fun getTopHeadLines() = viewModelScope.launch {
        when(val result = repository.getTopHeadlines()){
            is Resource.Success -> {
                _headlines.postValue(Resource.Success(result.data))
            }
            is Resource.Error -> {
                _headlines.postValue(Resource.Error(result.message!!))
            }
            is Resource.Loading -> {
                _headlines.postValue(Resource.Loading())
            }
        }
    }

    val topHeadLines = useCase.getNewsUseCase().asLiveData()

}
