package com.hadi.newsapp.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.hadi.newsapp.R
import com.hadi.newsapp.data.model.NewsResponse
import com.hadi.newsapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailsFragmentArgs>()

    private lateinit var article: NewsResponse.Article

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        initialize()

        return binding.root
    }

    private fun initialize() {
        article = args.newsArticle
        binding.tvNewsTitle.text = article.title
        binding.tvDate.text = article.publishedAt
        binding.tvAuthor.text = article.author
        binding.tvContent.text = article.content
        binding.ivThumbnail.load(article.urlToImage)

        binding.btnSeeFull.setOnClickListener {
            val action = DetailsFragmentDirections.actionDetailsFragmentToWebFragment(article.url)
            findNavController().navigate(action)
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }



}