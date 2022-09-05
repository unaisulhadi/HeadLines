package com.hadi.newsapp.presentation.web

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hadi.newsapp.R
import com.hadi.newsapp.databinding.FragmentWebBinding

class WebFragment : Fragment() {

    private var _binding: FragmentWebBinding? = null
    private val binding get() = _binding!!

    val args by navArgs<WebFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentWebBinding.inflate(inflater, container, false)
        setupWebView()
        return binding.root
    }

    private fun setupWebView() {

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(args.newsUrl)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}