package com.test.a1.ui.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.test.a1.App
import com.test.a1.R
import com.test.a1.databinding.FragmentTableDetailBinding
import com.test.a1.ui.viewmodels.TournamentViewModel
import com.test.a1.ui.ViewModelFactory
import com.test.a1.ui.adapter.TableDetailAdapter
import com.test.a1.ui.setTheme
import javax.inject.Inject


class TableDetailFragment : Fragment() {

    private val args: TableDetailFragmentArgs by navArgs()

    private var _binding: FragmentTableDetailBinding? = null
    private val binding: FragmentTableDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentTablesBinding == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: TournamentViewModel

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    private val adapter by lazy {
        TableDetailAdapter(
            LayoutInflater.from(requireContext()),
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTableDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[TournamentViewModel::class.java]
        initBackground(binding.imBackground,viewModel.wallpaper)
        with(binding){
            setTheme(viewModel.isDarkTheme,requireContext(),tvCountry)
        }
        when(args.country){
            getString(R.string.russia) -> viewModel.getTournamentInfo(1)
            getString(R.string.england) -> viewModel.getTournamentInfo(0)
            getString(R.string.spain) -> viewModel.getTournamentInfo(2)
            getString(R.string.germany) -> viewModel.getTournamentInfo(3)
         }

        initView()
        initObserver()
    }

    private fun initView() {
        binding.tvCountry.text = args.country
        initRecycler()
    }

    private fun initRecycler(){
        binding.rvCountry.adapter = adapter
    }

    private fun initObserver(){
        viewModel.detailTournamentListLD.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }
    }

    private fun initBackground(imageView: ImageView, wallpaper: Int){
        imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(),wallpaper))
    }
}
