package com.reeftankcare.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reeftankcare.database.Measurement
import com.reeftankcare.repository.MeasurementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeListViewModel : ViewModel() {

    //var hideProgress: (() -> Unit)? = null

    private val measurementRepository = MeasurementRepository.get()

   /* private var jobLoadMeasure: Job? = null

    private val progressStatus = MutableLiveData<Unit>()
*/
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
  /*  fun updateData() {
        jobLoadMeasure?.cancelChildren()
        jobLoadMeasure = viewModelScope.launch(Dispatchers.IO) {
                    measurementRepository.getMeasurement().collect {
                _measurements.value = it
            }
            progressStatus.postValue(Unit)
        }
    }*/
}
