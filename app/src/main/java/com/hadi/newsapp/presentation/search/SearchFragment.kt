package com.hadi.newsapp.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hadi.newsapp.R
import com.hadi.newsapp.databinding.FragmentSearchBinding
import com.hadi.newsapp.presentation.common.adapter.NewsPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel by viewModels<SearchViewModel>()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsPagingAdapter: NewsPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        observeSearchResult()
    }

    private fun observeSearchResult() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchResults.collect{
                    newsPagingAdapter.submitData(it)
                }

            }
        }



    }

    private fun initialize() {

        newsPagingAdapter = NewsPagingAdapter {

        }

        binding.rvSearchNews.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsPagingAdapter
        }

        viewModel.searchQuery.observe(viewLifecycleOwner){
            Timber.d("OkHttpSEARCH_QUERY=$it")

        }

        viewModel.results.observe(viewLifecycleOwner) {
            newsPagingAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }


//        viewModel.searchNews()

        binding.etSearch.doOnTextChanged { text, start, before, count ->
//            //binding.rvSearchNews.scrollToPosition(0)
            viewModel.searchQuery.value = text.toString()
//            viewModel.searchNews(text.toString())
        }

    }

}