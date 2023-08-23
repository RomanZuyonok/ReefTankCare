package com.reeftankcare.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GalleryItem(
    @Json(name = "title")val title: String,
    @Json(name = "id")val id: String,
    @Json(name="url_s") val url: String
)
