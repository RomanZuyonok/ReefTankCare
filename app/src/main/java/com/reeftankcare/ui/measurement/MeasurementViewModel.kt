package com.reeftankcare.ui.measurement

import androidx.lifecycle.ViewModel
import com.reeftankcare.database.Measurement

class MeasurementViewModel : ViewModel() {

    val measurement = mutableListOf<Measurement>()

    init {

    }

}