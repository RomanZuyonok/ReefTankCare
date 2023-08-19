package com.reeftankcare.network

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class FlickrResponse(
    val photos: PhotoResponse
)