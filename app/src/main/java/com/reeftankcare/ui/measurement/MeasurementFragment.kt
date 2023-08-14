package com.reeftankcare.ui.measurement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.reeftankcare.R
import com.reeftankcare.database.Measurement
import com.reeftankcare.databinding.FragmentMeasurementBinding
import com.reeftankcare.utilits.getString
import java.util.*
import androidx.core.widget.doAfterTextChanged
import com.reeftankcare.validation.ValidationResult
import com.reeftankcare.validation.inputValidationDouble
import com.reeftankcare.validation.inputValidationInteger

class MeasurementFragment : Fragment() {

    private lateinit var binding: FragmentMeasurementBinding

    private val measurementViewModel: MeasurementViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeasurementBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        measurementViewModel.run {
            measureSaved = {
                binding.root.post {
                    Toast.makeText(
                        requireContext(),
                        "Measurement saved in the database ...",
                        Toast.LENGTH_LONG
                    ).show()
                    parentFragmentManager.popBackStack()
                }
            }
            showError = {
                binding.root.post {
                    binding.temprTextField
                        .error =
                        requireContext().getString(R.string.err_input)
                }
            }

            showError = {
                binding.root.post {
                    binding.saltTextField
                        .error =
                        requireContext().getString(R.string.err_input)
                }
            }

            binding.buttonSaveMeasure.setOnClickListener {
                if (isValidate()){
                    addMeasurement()
                }
            }

            binding.saltEditText.doAfterTextChanged {
                isValidate()
            }
        }
}
    private fun isValidate(): Boolean {
        val ValidationResultF = inputValidationDouble(binding.temprEditText.getString())
        when (ValidationResultF) {
            is ValidationResult.Invalid -> binding.temprTextField.error =
                requireContext().getString(ValidationResultF.errorId)
            else -> binding.temprTextField.error = null
        }
        val isValid = ValidationResultF == ValidationResult.Valid
        binding.buttonSaveMeasure.isEnabled = isValid
        return isValid
    }
         private fun addMeasurement() {
             measurementViewModel.addNewMeasure(
                 Measurement(
                     0,
                     binding.temprEditText.getString().toDouble(),
                     binding.saltEditText.getString().toDouble(),
                     binding.khEditText.getString().toDouble(),
                     binding.caEditText.getString().toInt(),
                     binding.mgEditText.getString().toInt(),
                     binding.no3EditText.getString().toDouble(),
                     binding.po4EditText.getString().toDouble(),
                     Date()
                 )
             )
         }
}