package com.reeftankcare.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.reeftankcare.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeListFragment @Inject constructor(): Fragment() {

    private var bindingOne: FragmentHomeBinding? = null
    private val binding
        get() = checkNotNull(bindingOne) {}

    private val homeListViewModel: HomeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingOne = FragmentHomeBinding.inflate(inflater, container, false)
        binding.measureRecyclerView.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeListViewModel.measurements.collect { measurements ->
                    binding.measureRecyclerView.adapter =
                        HomeListAdapter(measurements) { measureID ->
                            findNavController().navigate(
                                HomeListFragmentDirections
                                    .showMeasurementDetail(measureID)
                            )
                        }
                    if (measurements.isNotEmpty()) {
                        binding.emptyRVTextView.visibility = View.GONE
                    }
                }
            }
        }

        binding.newMeasurementButton.setOnClickListener {
            findNavController().navigate(HomeListFragmentDirections.addNewMeasure())
        }

        binding.changeWaterButton.setOnClickListener {
            findNavController().navigate(HomeListFragmentDirections.goToWaterChange())

        }

        binding.photoTextButton.setOnClickListener{
            findNavController().navigate(HomeListFragmentDirections.goToPhoto())
        }

        binding.profileTextButton.setOnClickListener{
            findNavController().navigate(HomeListFragmentDirections.goToProfile())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingOne = null
    }

}