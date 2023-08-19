package com.reeftankcare.ui.photo_base

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reeftankcare.network.GalleryItem
import com.reeftankcare.repository.PhotoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "PhotoBaseViewModel"

class PhotoBaseViewModel : ViewModel() {
    private val photoResponse = PhotoRepository()

    private val _galletyItem: MutableStateFlow<List<GalleryItem>> =
        MutableStateFlow(emptyList())
    val galleryItem: StateFlow<List<GalleryItem>>
    get() = _galletyItem.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val item = PhotoRepository().fetchPhotos()
                Log.d(ContentValues.TAG, "Response : $item")
                _galletyItem.value = item
            } catch (ex: Exception) {
                Log.e(ContentValues.TAG, "Failed to fetch gallery item", ex)
            }
        }
    }
}