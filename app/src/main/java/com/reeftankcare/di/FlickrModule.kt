package com.reeftankcare.di

import com.reeftankcare.network.FlickrApi
import com.reeftankcare.network.PhotoInterceptor
import com.reeftankcare.utilits.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FlickrModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(PhotoInterceptor())
            .build()

    @Provides
    @Singleton
    fun provideFlickrAPI(
        okHttpClient: OkHttpClient,
    ): FlickrApi = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(
            MoshiConverterFactory
                .create()
        ).client(okHttpClient)
        .build().create(FlickrApi::class.java)
}