package com.reeftankcare.ui.measurement_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.reeftankcare.database.Measurement
import com.reeftankcare.repository.MeasurementRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MeasurementDetailsViewModel(measureId: Long) : ViewModel() {

    private val measurementRepository = MeasurementRepository.get()

    private val _measurement: MutableStateFlow<Measurement?> = MutableStateFlow(null)
    val measurements: StateFlow<Measurement?>
        get() = _measurement.asStateFlow()

    init {
        viewModelScope.launch {
            _measurement.value = measurementRepository.getMeasurement(measureId)
        }
    }

  /*  fun updateMeasure(onUpdate: (Measurement)-> Measurement){
        _measurement.update { oldMeasure -> oldMeasure?.let{onUpdate(it)} }
    }*/
}

class MeasurementDetailViewModelFactory(
    private val measureId: Long
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MeasurementDetailsViewModel(measureId) as T
    }
}

