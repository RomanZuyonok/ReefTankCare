package com.reeftankcare.utilits

import androidx.room.TypeConverter
import java.util.Date

class MeasurementTypeConverters {
    @TypeConverter
    fun fromDate(date: Date): Long{
        return date.time
    }

    @TypeConverter
    fun toDate(millisSince: Long): Date{
        return Date(millisSince)
    }
}