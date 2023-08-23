package com.reeftankcare.ui.photo_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PhotoDetailViewModel(photoId: String) : ViewModel() {

    fun init(photoId: String) {
        viewModelScope.launch {

        }
    }
}

