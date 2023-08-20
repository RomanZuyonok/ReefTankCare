package com.reeftankcare.model

import com.reeftankcare.network.GalleryItem

data class PhotoBaseUiState(
    val images: List<GalleryItem> = listOf(),
    val query: String = "",
)