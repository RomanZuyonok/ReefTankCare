package com.reeftankcare.repository

import com.reeftankcare.network.FlickrApi
import com.reeftankcare.network.GalleryItem
import com.reeftankcare.network.PhotoIdInterceptor
import com.reeftankcare.network.PhotoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

private const val BASE_URL = "https://api.flickr.com/"

class PhotoRepository {
    private val flickrApi: FlickrApi

    init {
        val okHttpClient1 = OkHttpClient
            .Builder()
            .addInterceptor(PhotoInterceptor())
            .build()

        val okHttpClient2 = OkHttpClient
            .Builder()
            .addInterceptor(PhotoIdInterceptor())
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory
                    .create()
            ).client(okHttpClient1)
            .build()

        flickrApi = retrofit.create()
    }

    suspend fun fetchPhotos(): List<GalleryItem> = flickrApi
        .fetchPhotos()
        .photos
        .galleryItems

    suspend fun searchPhotos(query: String): List<GalleryItem> = flickrApi
        .searchPhoto(query)
        .photos
        .galleryItems

    suspend fun searchById(query: String): List<GalleryItem> = flickrApi
        .searchById(query)
        .photos
        .galleryItems
}