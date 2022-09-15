package com.hadi.newsapp.presentation.search

import androidx.lifecycle.ViewModel
import com.hadi.newsapp.domain.usecases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {



}
