package com.test.a1.ui.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.test.a1.App
import com.test.a1.R
import com.test.a1.databinding.FragmentStatisticBinding
import com.test.a1.ui.entities.AttackDefence
import com.test.a1.ui.viewmodels.AttackDefenceViewModel
import com.test.a1.ui.ViewModelFactory
import com.test.a1.ui.adapter.AttackAdapter
import com.test.a1.ui.adapter.DefenceAdapter
import com.test.a1.ui.setTheme
import javax.inject.Inject

class StatisticFragment : Fragment() {

    private var _binding: FragmentStatisticBinding? = null
    private val binding: FragmentStatisticBinding
        get() = _binding ?: throw RuntimeException("FragmentStatisticBinding == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: AttackDefenceViewModel

    private var defenceList = listOf<AttackDefence>()
    private var attackList = listOf<AttackDefence>()

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    private val defenceAdapter by lazy {
        DefenceAdapter(
            LayoutInflater.from(requireContext()),
        )
    }

    private val attackAdapter by lazy {
        AttackAdapter(
            LayoutInflater.from(requireContext()),
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatisticBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[AttackDefenceViewModel::class.java]
        initBackground(binding.imBackground,viewModel.wallpaper)
        with(binding){
            setTheme(viewModel.isDarkTheme,requireContext(),tvAttack,tvDefence,tvStatisticTitle)
        }
        viewModel.getStatistic()
        initRecycler()
        initObserver()
        initClickListener()

    }

    private fun initObserver() {
        viewModel.attackListLD.observe(viewLifecycleOwner) {
            attackList = it
        }
        viewModel.defenceListLD.observe(viewLifecycleOwner) {
            defenceList = it
            defenceAdapter.updateList(it)
        }
    }

    private fun initClickListener() {
        binding.tvAttack.setOnClickListener {
            binding.rvStatistic.adapter = attackAdapter
            attackAdapter.updateList(attackList)
            binding.tvDefence.setBackgroundResource(R.drawable.blue_background)
            it.setBackgroundResource(R.drawable.orange_background)
        }

        binding.tvDefence.setOnClickListener {
            binding.rvStatistic.adapter = defenceAdapter
            defenceAdapter.updateList(defenceList)
            it.setBackgroundResource(R.drawable.orange_background)
            binding.tvAttack.setBackgroundResource(R.drawable.blue_background)

        }
    }

    private fun initRecycler() {
        binding.rvStatistic.adapter = defenceAdapter
    }

    private fun initBackground(imageView: ImageView, wallpaper: Int){
        imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(),wallpaper))
    }


}
