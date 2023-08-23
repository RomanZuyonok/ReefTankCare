package com.reeftankcare.repository

import com.reeftankcare.network.FlickrApi
import com.reeftankcare.network.GalleryItem
import javax.inject.Inject

class PhotoRepository @Inject constructor(private var flickrApi: FlickrApi) {

    suspend fun fetchPhotos(): List<GalleryItem> = flickrApi
        .fetchPhotos()
        .photos
        .galleryItems

    suspend fun searchPhotos(query: String): List<GalleryItem> = flickrApi
        .searchPhoto(query)
        .photos
        .galleryItems
}