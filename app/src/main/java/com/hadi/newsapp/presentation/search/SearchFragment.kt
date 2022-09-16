package com.hadi.newsapp.presentation.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.hadi.newsapp.R
import com.hadi.newsapp.databinding.FragmentSearchBinding
import com.hadi.newsapp.presentation.common.adapter.NewsPagingAdapter
import com.hadi.newsapp.utils.hide
import com.hadi.newsapp.utils.onDone
import com.hadi.newsapp.utils.shortToast
import com.hadi.newsapp.utils.show
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
    }

    private fun initialize() {



        newsPagingAdapter = NewsPagingAdapter {
            val action = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(it)
            findNavController().navigate(action)
        }

        binding.ivBackSnews.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.rvSearchNews.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsPagingAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.resultsFlow.collect{ pagingData ->
                    newsPagingAdapter.submitData(lifecycle, pagingData)
                }
            }
        }

        newsPagingAdapter.addLoadStateListener { state ->
            when(state.refresh){
                is LoadState.NotLoading -> {
                    binding.progressSearch.hide()
                }
                is LoadState.Loading -> {
                    binding.progressSearch.show()
                }
                is LoadState.Error -> {
                    binding.progressSearch.hide()
                    requireContext().shortToast("Error occurred!")
                }
            }
        }

        binding.etSearch.onDone { text ->
            if(text.isNullOrBlank()){
                newsPagingAdapter.submitData(lifecycle, PagingData.empty())
            }else{
                viewModel.searchQuery.value = text
            }
        }

    }

}