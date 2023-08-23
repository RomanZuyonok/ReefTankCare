package com.reeftankcare.ui.measurement_detail

import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.reeftankcare.database.Measurement
import com.reeftankcare.repository.MeasurementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MeasurementDetailsViewModel @Inject constructor(private val measurementRepository: MeasurementRepository) :
    ViewModel() {

    private val _measurement: MutableStateFlow<Measurement?> = MutableStateFlow(null)
    val measurements: StateFlow<Measurement?>
        get() = _measurement.asStateFlow()

    fun init(measureId: Long) {
        viewModelScope.launch {
            _measurement.value = measurementRepository.getMeasurement(measureId)
        }
    }
}

