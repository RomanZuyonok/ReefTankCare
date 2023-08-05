package com.reeftankcare.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Measurement(
    @PrimaryKey val id: UUID,

    val temperature: Double,

    val salinity: Double,

    val kh: Double,

    val calcium: Int,

    val magnesium: Int,

    val no3: Double,

    val po4: Double,

    val date: Date
)
