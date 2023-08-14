package com.reeftankcare.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface MeasurementDao {
    @Query("SELECT * FROM Measurements")
    fun getMeasurements(): Flow<List<Measurement>>
    @Query("SELECT * FROM Measurements WHERE id=(:id)")
    suspend fun getMeasurement(id: Long): Measurement
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMeasurement(measurement: Measurement)
    @Update
    suspend fun updateMeasurement(measurement: Measurement)
    @Delete
    suspend fun deleteMeasurement(measurement: Measurement)
}