package com.reeftankcare.di

import android.content.Context
import androidx.room.Room
import com.reeftankcare.database.MeasurementDao
import com.reeftankcare.database.MeasurementDataBase
import com.reeftankcare.utilits.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideMeasurementDao(@ApplicationContext context: Context): MeasurementDao {
        return Room.databaseBuilder(
            context,
            MeasurementDataBase::class.java,
            Constants.DATABASE_NAME,
        )
            .build()
            .measurementDao()
    }
}