package com.reeftankcare.ui.photo_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PhotoDetailViewModel(photoId: String) : ViewModel() {

    init {
        viewModelScope.launch {

        }
    }
}
class PhotoDetailViewModelFactory(
    private val photoId: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhotoDetailViewModel(photoId) as T
    }
}