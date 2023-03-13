package com.test.a1.ui.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.a1.App
import com.test.a1.databinding.FragmentNewsBinding
import com.test.a1.ui.viewmodels.NewsVewModel
import com.test.a1.ui.ViewModelFactory
import com.test.a1.ui.adapter.NewsAdapter
import com.test.a1.ui.setTheme
import javax.inject.Inject


class NewsFragment : Fragment() {


    private var _binding: FragmentNewsBinding? = null
    private val binding: FragmentNewsBinding
        get() = _binding ?: throw RuntimeException("FragmentNewsBinding == null")

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    private val adapter by lazy {
        NewsAdapter(
            LayoutInflater.from(requireContext()),
        )
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: NewsVewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[NewsVewModel::class.java]
        initBackground(binding.imBackground,viewModel.wallpaper)
        with(binding){
            setTheme(viewModel.isDarkTheme,requireContext(),textView)
        }
        viewModel.getNews()
        initRecycler()
        initObserver()
    }

    private fun initRecycler() {
        binding.rvNews.adapter = adapter
    }

    private fun initObserver() {
        viewModel.newsListLD.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }
    }

    private fun initBackground(imageView: ImageView, wallpaper: Int){
        imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(),wallpaper))
    }

}