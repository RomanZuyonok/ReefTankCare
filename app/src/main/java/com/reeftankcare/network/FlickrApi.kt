package com.reeftankcare.network

import retrofit2.http.GET
import retrofit2.http.Query


interface FlickrApi {

    @GET("services/rest/?method=flickr.interestingness.getList")
    suspend fun fetchPhotos(): FlickrResponse

    @GET("services/rest/?method=flickr.photos.search")
    suspend fun searchPhoto(@Query("text") query: String) : FlickrResponse

    @GET("services/rest/?method=flickr.photos.getInfo")
      suspend fun searchById(@Query("photo_id") query: String) : FlickrResponse
}