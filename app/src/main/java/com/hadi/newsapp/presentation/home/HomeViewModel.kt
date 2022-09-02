package com.hadi.newsapp.presentation.home

import androidx.lifecycle.*
import com.hadi.newsapp.data.model.NewsResponse
import com.hadi.newsapp.domain.usecases.UseCases
import com.hadi.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: UseCases
) : ViewModel() {

    private var _headlines = MutableLiveData<Resource<NewsResponse>>()
    var headlines : LiveData<Resource<NewsResponse>>  = _headlines


    private var _everything = MutableLiveData<Resource<NewsResponse>>()
    var everything : LiveData<Resource<NewsResponse>>  = _everything

    init {
        getTopHeadLines()
        getEverything()
    }

    private fun getTopHeadLines() = viewModelScope.launch {
        headlines = useCase.getNewsUseCase().asLiveData()
    }

    private fun getEverything() = viewModelScope.launch {
        everything = useCase.getEverythingUseCase().asLiveData()
    }



}
