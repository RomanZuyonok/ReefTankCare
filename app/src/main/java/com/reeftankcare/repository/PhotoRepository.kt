package com.reeftankcare.repository

import com.reeftankcare.api.PhotoApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class PhotoRepository {

    private val photoApi: PhotoApi? = null

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://random-d.uk/api/v2")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        // val photoApi: PhotoApi = retrofit.create<PhotoApi>()
    }

 //   suspend fun fetchPhotos(): List<GalleryItem> = photoApi?.fetchPhoto().photos.galleryItems

}