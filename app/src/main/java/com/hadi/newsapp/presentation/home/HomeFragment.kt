package com.hadi.newsapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hadi.newsapp.R
import com.hadi.newsapp.databinding.FragmentHomeBinding
import com.hadi.newsapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()

    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        Timber.d("VIEWMODEL=${viewModel.text}")

        viewModel.headlines.observe(viewLifecycleOwner) { response ->
            Timber.d("VIEWMODEL=OBSERVING")
            when (response) {
                is Resource.Success -> {
                    Timber.d("VIEWMODEL=SUCCESS")
                    Timber.d("VIEWMODEL=${response.data?.status}")
                }
                is Resource.Error -> {
                    Timber.d("VIEWMODEL=ERROR")
                }
                is Resource.Loading -> {
                    Timber.d("VIEWMODEL=LOADING")
                }
                else -> {
                    Timber.d("VIEWMODEL=SOMETHING WRONG")
                }
            }


        }



        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}