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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.test.a1.App
import com.test.a1.R
import com.test.a1.databinding.FragmentMainBinding
import com.test.a1.databinding.FragmentTablesBinding
import com.test.a1.ui.ViewModelFactory
import com.test.a1.ui.setTheme
import com.test.a1.ui.viewmodels.OptionsViewModel
import javax.inject.Inject


class TablesFragment : Fragment() {

    private var _binding: FragmentTablesBinding? = null
    private val binding: FragmentTablesBinding
        get() = _binding ?: throw RuntimeException("FragmentTablesBinding == null")

    private val component by lazy {
        (requireActivity().application as App).component
    }


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: OptionsViewModel


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTablesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[OptionsViewModel::class.java]
        initBackground(binding.imBackground,viewModel.wallpaper)
        with(binding){
            setTheme(viewModel.isDarkTheme,requireContext(),tvSpain,tvGermany,tvEngland,tvRussia,tvTables)
        }
        initClickListeners()
    }

    private fun initClickListeners() {
        with(binding) {
            tvRussia.setOnClickListener {
                val action =
                    TablesFragmentDirections.actionTablesFragmentToTableDetailFragment(tvRussia.text.toString())
                findNavController().navigate(action)
            }
            tvEngland.setOnClickListener {
                val action =
                    TablesFragmentDirections.actionTablesFragmentToTableDetailFragment(tvEngland.text.toString())
                findNavController().navigate(action)

            }
            tvGermany.setOnClickListener {
                val action =
                    TablesFragmentDirections.actionTablesFragmentToTableDetailFragment(tvGermany.text.toString())
                findNavController().navigate(action)

            }
            tvSpain.setOnClickListener {
                val action =
                    TablesFragmentDirections.actionTablesFragmentToTableDetailFragment(tvSpain.text.toString())
                findNavController().navigate(action)
            }
        }
    }

    private fun initBackground(imageView: ImageView, wallpaper: Int){
        imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(),wallpaper))
    }

}