package com.reeftankcare.repository

import com.reeftankcare.api.FlickrApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class PhotoRepository {

    private val flickrApi: FlickrApi? = null

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("api://flickr.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        // val flickrApi: FlickrApi = retrofit.create<FlickrApi>()
    }

 //   suspend fun fetchPhotos(): List<GalleryItem> = flickrApi?.fetchPhoto().photos.galleryItems

}