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
import com.reeftankcare.R
import com.reeftankcare.databinding.FragmentHomeBinding
import com.reeftankcare.ui.measurement.MeasurementFragment
import kotlinx.coroutines.launch

class HomeListFragment : Fragment() {

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
        /* binding.swiperRefreshLayout.setColorSchemeResources(
             android.R.color.holo_blue_bright,
             android.R.color.holo_green_light,
             android.R.color.holo_orange_light,
             android.R.color.holo_red_light
         )
         binding.swiperRefreshLayout.setOnRefreshListener {
             if (!binding.swiperRefreshLayout.isRefreshing) {
                 homeListViewModel.updateData()
                 binding.swiperRefreshLayout.isRefreshing = true
             } else {
                 Toast.makeText(
                     requireContext(),
                     "Please wait loading...",
                     Toast.LENGTH_LONG
                 ).show()
                 binding.swiperRefreshLayout.isRefreshing = false
             }
         }*/

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

        }

        binding.photoTextButton.setOnClickListener {
            findNavController().navigate(HomeListFragmentDirections.goToPhoto())
        }

        binding.profileTextButton.setOnClickListener {
            findNavController().navigate(HomeListFragmentDirections.goToProfile())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingOne = null
    }
}