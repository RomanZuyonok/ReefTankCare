package com.reeftankcare

import android.app.Application
import com.reeftankcare.repository.MeasurementRepository
import com.reeftankcare.repository.PreferencesRepository

class ReefTankCareApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MeasurementRepository.initialize(this)
        PreferencesRepository.initialize(this)
    }
}