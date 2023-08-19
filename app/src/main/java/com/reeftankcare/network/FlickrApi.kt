package com.reeftankcare.network

import retrofit2.http.GET

private const val API_KEY = "9440c03e843a60a0839fe0fa74ed25a0"

interface FlickrApi {

    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=$API_KEY" +
                "&format=json" +
                "&nojsoncallback=1"+
                "&extras=url_s"
    )
    suspend fun fetchPhotos(): FlickrResponse
}