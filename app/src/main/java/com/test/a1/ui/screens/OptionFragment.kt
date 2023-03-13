package com.test.a1.ui.screens

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.test.a1.App
import com.test.a1.R
import com.test.a1.databinding.FragmentOptionBinding
import com.test.a1.ui.ViewModelFactory
import com.test.a1.ui.setTheme
import com.test.a1.ui.viewmodels.OptionsViewModel
import javax.inject.Inject


class OptionFragment : Fragment() {


    private var _binding: FragmentOptionBinding? = null
    private val binding: FragmentOptionBinding
        get() = _binding ?: throw RuntimeException("FragmentOptionBinding == null")

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
        _binding = FragmentOptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[OptionsViewModel::class.java]
        initBackground(binding.imBackground, viewModel.wallpaper)
        setOnclickListeners()
        changeThemeSwitch()
        with(binding) {
            setTheme(
                viewModel.isDarkTheme,
                requireContext(),
                tvTheme,
                tvWallPaper,
                tvChange,
                tvNotification,
                tvOptionsTitle
            )
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_optionFragment_to_mainFragment)
            }
        })
    }


    private fun setOnclickListeners() {
        binding.tvChange.setOnClickListener {
            val wallpaper = viewModel.changeWallpaper()
            binding.imBackground.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    wallpaper
                )
            )
        }
    }

    private fun changeThemeSwitch() {
        with(binding) {
            switchTheme.isChecked = viewModel.isDarkTheme
            switchTheme.setOnCheckedChangeListener { compoundButton, b ->
                viewModel.changeTheme(b)
                with(binding) {
                    setTheme(
                        b,
                        requireContext(),
                        tvTheme,
                        tvWallPaper,
                        tvChange,
                        tvNotification,
                        tvOptionsTitle
                    )
                }
            }
        }
    }

    private fun initBackground(imageView: ImageView, wallpaper: Int) {
        imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(), wallpaper))
    }
}