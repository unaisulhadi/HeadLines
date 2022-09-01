package com.hadi.newsapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.hadi.newsapp.R
import com.hadi.newsapp.databinding.FragmentHomeBinding
import com.hadi.newsapp.presentation.common.adapter.TopHeadlineAdapter
import com.hadi.newsapp.utils.Resource
import com.hadi.newsapp.utils.gone
import com.hadi.newsapp.utils.shortToast
import com.hadi.newsapp.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()

    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!

    private val topHeadlinesAdapter by lazy { TopHeadlineAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        initUI()
        getTopHeadlines()




        return binding.root
    }

    private fun initUI() {
        binding.rvTrending.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = topHeadlinesAdapter
        }
    }

    private fun getTopHeadlines() {
        viewModel.headlines.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progress.gone()
                    response.data?.let { topHeadLines ->
                        if(topHeadLines.articles.isNullOrEmpty()){
                            requireContext().shortToast("No articles found!")
                        }else{
                            topHeadlinesAdapter.submitList(topHeadLines.articles)
                        }

                    }
                }
                is Resource.Error -> {
                    requireContext().shortToast(response.message!!)
                    binding.progress.gone()
                }
                is Resource.Loading -> {
                    binding.progress.show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}