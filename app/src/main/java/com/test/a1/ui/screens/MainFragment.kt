package com.test.a1.ui.screens

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.onesignal.OneSignal
import com.test.a1.App
import com.test.a1.R
import com.test.a1.databinding.FragmentMainBinding
import com.test.a1.ui.ViewModelFactory
import com.test.a1.ui.setTheme
import com.test.a1.ui.viewmodels.NewsVewModel
import javax.inject.Inject


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    private val args: MainFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: NewsVewModel

    private val component by lazy {
        (requireActivity().application as App).component
    }
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.i("Permission: ", "Granted")
            } else {
                Log.i("Permission: ", "Denied")
            }
        }


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =FragmentMainBinding.inflate(inflater,container,false)
         return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cancelPush()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermission(view,android.Manifest.permission.POST_NOTIFICATIONS)
        }

        viewModel = ViewModelProvider(this, viewModelFactory)[NewsVewModel::class.java]
        initBackground(binding.imBackground,viewModel.wallpaper)
        with(binding){
            setTheme(viewModel.isDarkTheme,requireContext(),tvNews,tvOptions,tvStatistic,tvTables)
        }
         initClickListeners()
     }

    private fun initClickListeners(){
        with(binding){
            tvTables.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_tablesFragment)
            }
            tvNews.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_newsFragment)
            }
            tvStatistic.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_statisticFragment)
            }
            tvOptions.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_optionFragment)
            }
        }
    }


    private fun initBackground(imageView: ImageView, wallpaper: Int){
        imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(),wallpaper))
    }

    private fun cancelPush(){
        if(args.push == SplashFragment.Companion.NO)
            OneSignal.disablePush(true)
        else
            OneSignal.disablePush(false)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        permissions.forEach {
            Log.d("PERMISSION",it)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    private fun requestPermission(view: View, permission: String){

        when {
            ContextCompat.checkSelfPermission(
                requireActivity(),
                permission
            ) == PackageManager.PERMISSION_GRANTED -> {

            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                permission
            ) -> {
                requestPermissionLauncher.launch(
                    permission
                )
            }

            else -> {
                requestPermissionLauncher.launch(
                    permission
                )
            }
        }
    }

}
