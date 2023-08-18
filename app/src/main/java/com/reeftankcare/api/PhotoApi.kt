package com.reeftankcare.api

import retrofit2.http.GET

private const val API_KEY = "key"

interface PhotoApi {
    @GET(
        "services/rest/?method=flickr.interestingness.getList"+
                "&api_key=$API_KEY"+
                "&format=json"+
                "&nojsoncallback=1"+
                "&extras=url_s"
    )
    suspend fun fetchContents(): String
}