package com.reeftankcare.ui.measurement_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reeftankcare.database.Measurement
import com.reeftankcare.repository.MeasurementRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MeasurementDetailsViewModel : ViewModel() {

    private val measurementRepository = MeasurementRepository.get()

    private val measurements1 : MutableStateFlow<List<Measurement>> =
        MutableStateFlow(emptyList())
    val measurements: StateFlow<List<Measurement>>
        get() = measurements1.asStateFlow()

    init {
        viewModelScope.launch {
            measurementRepository.getMeasurement().collect{
                measurements1.value = it
            }
        }
    }

}