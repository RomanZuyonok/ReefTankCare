package com.reeftankcare.ui.measurement_detail

import android.icu.text.DateFormat
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
import androidx.navigation.fragment.navArgs
import com.reeftankcare.database.Measurement
import com.reeftankcare.databinding.FragmentMeasureDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@AndroidEntryPoint
class MeasurementDetailsFragment : Fragment() {

    private var bindingOne: FragmentMeasureDetailsBinding? = null
    private val binding
        get() = checkNotNull(bindingOne) {}

    private val measurementDetailsViewModel: MeasurementDetailsViewModel by viewModels()

    private val args: MeasurementDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingOne = FragmentMeasureDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        measurementDetailsViewModel.init(args.measureID)

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                measurementDetailsViewModel.measurements.collect { measurement ->
                    measurement?.let {
                        bindDetail(it)
                    }
                }
            }
        }

        binding.buttonBackHome.setOnClickListener{
          findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingOne = null
    }

    private fun updateUi(measurement: Measurement) {
        binding.apply {
            titleDateTextView.text = DateFormat.getDateInstance().format(measurement.date)
            measureTemperatureTextView.text = measurement.temperature.toString()
            measureSaltTextView.text = measurement.salinity.toString()
            measureKhTextView.text = measurement.kh.toString()
            measureCaTextView.text = measurement.calcium.toString()
            measureMgTextView.text = measurement.magnesium.toString()
            measureNo3TextView.text = measurement.no3.toString()
            measureNNo3TextView.text = if (measurement.no3 > 0.0) {
                (((measurement.no3 / 3.2)* 100.0).roundToInt() / 100.0).toString()
            } else {
                "0.0"
            }
            measurePo4TextView.text = measurement.po4.toString()
            measurePPo4TextView.text = if (measurement.po4 > 0.0) {
                (((measurement.po4 / 4.1)* 100.0).roundToInt() / 100.0).toString()
            } else {
                "0.0"
            }
        }
    }

    private fun bindDetail(measurement: Measurement) {
        binding.apply {
            titleDateTextView.text = DateFormat.getDateInstance().format(measurement.date)
            measureTemperatureTextView.text = measurement.temperature.toString()
            measureSaltTextView.text = measurement.salinity.toString()
            measureKhTextView.text = measurement.kh.toString()
            measureCaTextView.text = measurement.calcium.toString()
            measureMgTextView.text = measurement.magnesium.toString()
            measureNo3TextView.text = measurement.no3.toString()
            measureNNo3TextView.text = if (measurement.no3 > 0.0) {
                (((measurement.no3 / 3.2)* 100.0).roundToInt() / 100.0).toString()
            } else {
                "0.0"
            }
            measurePo4TextView.text = measurement.po4.toString()
            measurePPo4TextView.text = if (measurement.po4 > 0.0) {
                (((measurement.po4 / 4.1)* 100.0).roundToInt() / 100.0).toString()
            } else {
                "0.0"
            }
        }
    }
}