package com.reeftankcare.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.reeftankcare.utilits.MeasurementTypeConverters

    @Database(entities = [Measurement::class], version = 1)
    @TypeConverters(MeasurementTypeConverters::class)
    abstract class MeasurementDataBase : RoomDatabase(){
        abstract fun measurementDao() : MeasurementDao
    }
