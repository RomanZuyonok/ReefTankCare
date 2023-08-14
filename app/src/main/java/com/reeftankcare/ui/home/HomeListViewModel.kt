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
import kotlinx.coroutines.launch

class HomeListViewModel : ViewModel() {

    private val measurementRepository = MeasurementRepository.get()

    private var jobLoadMeasure: Job? = null

    //var hideProgress: (() -> Unit)? = null

    private val progressStatus = MutableLiveData<Unit>()

    private val measurements1: MutableStateFlow<List<Measurement>> =
        MutableStateFlow(emptyList())
    val measurements: StateFlow<List<Measurement>>
        get() = measurements1.asStateFlow()

    init {
        viewModelScope.launch {
            measurementRepository.getMeasurement().collect {
                measurements1.value = it
            }
        }
    }
    fun updateData() {
        jobLoadMeasure?.cancelChildren()
        jobLoadMeasure = viewModelScope.launch(Dispatchers.IO) {
                    measurementRepository.getMeasurement().collect {
                measurements1.value = it
            }
            progressStatus.postValue(Unit)
        }
    }
}
