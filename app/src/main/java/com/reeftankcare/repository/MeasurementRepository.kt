package com.reeftankcare.repository

import android.content.Context
import androidx.room.Room
import com.reeftankcare.database.Measurement
import com.reeftankcare.database.MeasurementDataBase
import java.util.*

private const val DATABASE_NAME = "measurement-database"

class MeasurementRepository private constructor(context: Context) {

    private val database: MeasurementDataBase = Room
        .databaseBuilder(
            context.applicationContext,
            MeasurementDataBase::class.java,
            DATABASE_NAME
        )
        .createFromAsset(DATABASE_NAME)
        .build()

    suspend fun getMeasurement(): List<Measurement> = database.measurementDao().getMeasurements()

    suspend fun getMeasurement(id: UUID): Measurement = database.measurementDao().getMeasurement(id)

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