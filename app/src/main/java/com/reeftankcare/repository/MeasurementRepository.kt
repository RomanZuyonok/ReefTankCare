package com.reeftankcare.repository

import android.content.Context
import androidx.room.Room
import com.reeftankcare.database.Measurement
import com.reeftankcare.database.MeasurementDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.*

private const val DATABASE_NAME = "measurement-database"

class MeasurementRepository private constructor(context: Context,
private val coroutineScope: CoroutineScope = GlobalScope) {

    private val database: MeasurementDataBase = Room
        .databaseBuilder(
            context.applicationContext,
            MeasurementDataBase::class.java,
            DATABASE_NAME
        )
        .build()

    fun getMeasurement(): Flow<List<Measurement>> = database.measurementDao().getMeasurements()

    suspend fun getMeasurement(id: UUID): Measurement = database.measurementDao().getMeasurement(id)

    suspend fun addMeasurement(measurement: Measurement){
        database.measurementDao()
            .addMeasurement(measurement)
    }

    fun updateMeasurement(measurement: Measurement){
        coroutineScope.launch {
            database.measurementDao()
                .updateMeasurement(measurement)  }
    }

    suspend fun deleteMeasurement(measurement: Measurement){
        database.measurementDao().deleteMeasurement(measurement)
    }

    companion object {
        private var INSTANCE: MeasurementRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = MeasurementRepository(context)
            }
        }

        @Throws(IllegalStateException::class)
        fun get(): MeasurementRepository {
            return INSTANCE
                ?: throw IllegalStateException("Error: MeasurementRepository must be initialized!")
        }
    }
}