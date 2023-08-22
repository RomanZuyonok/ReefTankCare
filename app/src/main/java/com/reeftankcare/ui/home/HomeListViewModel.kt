package com.reeftankcare.ui.home

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
class HomeListViewModel @Inject constructor(
    private var measurementRepository: MeasurementRepository,
): ViewModel() {

    private val _measurements: MutableStateFlow<List<Measurement>> =
        MutableStateFlow(emptyList())
    val measurements: StateFlow<List<Measurement>>
        get() = _measurements.asStateFlow()

    init {
        viewModelScope.launch {
            measurementRepository.getMeasurement().collect {
                _measurements.value = it
            }
        }
    }

}
