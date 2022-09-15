package com.hadi.newsapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.hadi.newsapp.data.model.NewsResponse
import com.hadi.newsapp.databinding.FragmentHomeBinding
import com.hadi.newsapp.presentation.common.adapter.NewsListAdapter
import com.hadi.newsapp.presentation.common.adapter.TopHeadlineAdapter
import com.hadi.newsapp.utils.Resource
import com.hadi.newsapp.utils.gone
import com.hadi.newsapp.utils.shortToast
import com.hadi.newsapp.utils.show
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var topHeadlinesAdapter: TopHeadlineAdapter
    private lateinit var allNewsAdapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initialize()
        getTopHeadlines()
        getEverything()



        return binding.root
    }

    private fun initialize() {


        allNewsAdapter = NewsListAdapter {
           gotoNewsDetails(it)
        }
        topHeadlinesAdapter = TopHeadlineAdapter {
            gotoNewsDetails(it)
        }

        binding.rvTrending.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = topHeadlinesAdapter
            binding.dotsIndicator.attachTo(this)
        }
        binding.rvMain.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            ))
            adapter = allNewsAdapter
        }

        binding.btnNext.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToNewsFragment("technology")
            findNavController().navigate(action)
        }

        binding.ivSearch.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(action)
        }

        binding.btnPrev.setOnClickListener {

        }
    }

    private fun gotoNewsDetails(news: NewsResponse.Article) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(news)
        findNavController().navigate(action)
    }

    private fun getTopHeadlines() {
        viewModel.headlines.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progress.gone()
                    response.data?.let { topHeadLines ->
                        if (topHeadLines.articles.isNullOrEmpty()) {
                            requireContext().shortToast("No articles found!")
                        } else {
                            //List only 5 items for the Sake of UI 😂😂
                            val items =
                                if (topHeadLines.articles.size > 5) topHeadLines.articles.take(5) else topHeadLines.articles
                            topHeadlinesAdapter.submitList(items)
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

    private fun getEverything() {
        viewModel.everything.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progress.gone()
                    response.data?.let { newsResponse ->
                        if (newsResponse.articles.isNullOrEmpty()) {
                            requireContext().shortToast("No articles found!")
                        } else {
                            allNewsAdapter.submitList(newsResponse.articles)
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