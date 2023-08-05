package com.reeftankcare.database

import androidx.room.Dao
import androidx.room.Query
import java.util.*

@Dao
interface MeasurementDao {
    /*  @Query("SELECT * FROM measurement")
      fun getMeasurements(): Flow<List<Measurement>>*/

    @Query("SELECT * FROM measurement")
    suspend fun getMeasurements(): List<Measurement>

    @Query("SELECT * FROM measurement WHERE id=(:id)")
    suspend fun getMeasurement(id: UUID): Measurement
}