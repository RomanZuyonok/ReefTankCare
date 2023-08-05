package com.reeftankcare

import android.app.Application
import com.reeftankcare.repository.MeasurementRepository

class ReefTankCareApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MeasurementRepository.initialize(this)
    }
}