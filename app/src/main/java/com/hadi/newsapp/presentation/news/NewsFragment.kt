package com.hadi.newsapp.presentation.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.hadi.newsapp.databinding.FragmentNewsBinding
import com.hadi.newsapp.presentation.common.adapter.NewsPagingAdapter
import com.hadi.newsapp.utils.Resource
import com.hadi.newsapp.utils.hide
import com.hadi.newsapp.utils.shortToast
import com.hadi.newsapp.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<NewsViewModel>()
    private val args by navArgs<NewsFragmentArgs>()

    private var newsAdapter = NewsPagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUI()
        viewModel.getNewsByCategory(args.category)
        observeNewsByCategory()
    }

    private fun initUI() {
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
    }

    private fun observeNewsByCategory() {

        // Flow -> Collecting flow from UI
//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.newsByCategoryFlow.collect{ response ->
//                    when(response){
//                        is Resource.Success -> {
//
//                        }
//                        is Resource.Error -> {
//
//                        }
//                        is Resource.Loading -> {
//
//                        }
//                    }
//                }
//            }
//        }

        newsAdapter.addLoadStateListener { state ->
            when(state.refresh){
                is LoadState.NotLoading -> {
                    binding.progress.hide()
                }
                LoadState.Loading -> {
                    binding.progress.show()
                }
                is LoadState.Error -> {
                    binding.progress.hide()
                    requireContext().shortToast("Error occurred!")
                }
            }
        }


        viewModel.newsByCategory.observe(viewLifecycleOwner){ pagingData ->
            newsAdapter.submitData(lifecycle, pagingData)
        }

    }


}