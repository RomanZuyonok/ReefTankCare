package com.reeftankcare.ui.measurement_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.reeftankcare.databinding.FragmentMeasureDetailsBinding

class MeasurementDetailsFragment : Fragment() {

    private lateinit var binding : FragmentMeasureDetailsBinding

    private val measurementDetaelsViewModel : MeasurementDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeasureDetailsBinding.inflate(inflater,container,false)

        return binding.root
    }
}