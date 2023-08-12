package com.reeftankcare.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface MeasurementDao {
    @Query("SELECT * FROM measurement")
    fun getMeasurements(): Flow<List<Measurement>>
    @Query("SELECT * FROM measurement WHERE id=(:id)")
    suspend fun getMeasurement(id: Long): Measurement
    @Insert
    suspend fun addMeasurement(measurement: Measurement)
    @Update
    suspend fun updateMeasurement(measurement: Measurement)
    @Delete
    suspend fun deleteMeasurement(measurement: Measurement)
}