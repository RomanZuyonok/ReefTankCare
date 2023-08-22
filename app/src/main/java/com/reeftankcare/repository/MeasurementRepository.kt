package com.reeftankcare.repository

import com.reeftankcare.database.Measurement
import com.reeftankcare.database.MeasurementDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MeasurementRepository @Inject constructor(
    private var measurementDao: MeasurementDao,
) {
    fun getMeasurement(): Flow<List<Measurement>> = measurementDao.getMeasurements()

    suspend fun getMeasurement(id: Long): Measurement = measurementDao.getMeasurement(id)

    suspend fun addMeasurement(measurement: Measurement): Boolean {
        measurementDao.addMeasurement(measurement)
        return true
    }
}