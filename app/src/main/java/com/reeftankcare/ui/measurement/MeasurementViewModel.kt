package com.reeftankcare.ui.measurement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reeftankcare.database.Measurement
import com.reeftankcare.repository.MeasurementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MeasurementViewModel : ViewModel() {

   private val measurementRepository = MeasurementRepository.get()

    var measureSaved: (() -> Unit)? = null

    var showError: (() -> Unit)? = null

    fun addNewMeasure(measurement: Measurement) {
        viewModelScope.launch(Dispatchers.IO) {
            val isMeasureSaved = measurementRepository.addMeasurement(measurement)
            if (isMeasureSaved) {
                measureSaved?.invoke()
            } else {
                showError?.invoke()
            }
        }
    }

}