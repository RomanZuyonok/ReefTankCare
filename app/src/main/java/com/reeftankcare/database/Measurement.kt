package com.reeftankcare.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Measurements")
data class Measurement(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long,
    @ColumnInfo("temperature")
    val temperature: Double,
    @ColumnInfo("salinity")
    val salinity: Double,
    @ColumnInfo("kh")
    val kh: Double,
    @ColumnInfo("calcium")
    val calcium: Int,
    @ColumnInfo("magnesium")
    val magnesium: Int,
    @ColumnInfo("no3")
    val no3: Double,
    @ColumnInfo("po4")
    val po4: Double,
    @ColumnInfo("date")
    val date: Date
)
