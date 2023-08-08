package com.reeftankcare.ui.measurement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reeftankcare.database.Measurement
import com.reeftankcare.databinding.FragmentMeasurementBinding
import java.util.*

class MeasurementFragment : Fragment() {

    private lateinit var binding: FragmentMeasurementBinding

    private lateinit var measurement: Measurement

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        measurement = Measurement(
            id = UUID.randomUUID(),
            temperature = 25.0,
            salinity = 35.0,
            7.8,
            425,
            magnesium = 1260,
            no3 = 0.25,
            po4 = 0.02,
            date = Date()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeasurementBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

        }
    }

}