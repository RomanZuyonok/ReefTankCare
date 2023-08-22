package com.reeftankcare.ui.photo_base

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.query
import com.reeftankcare.model.PhotoBaseUiState
import com.reeftankcare.network.GalleryItem
import com.reeftankcare.repository.PhotoRepository
import com.reeftankcare.repository.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "PhotoBaseViewModel"
@HiltViewModel
class PhotoBaseViewModel @Inject constructor(
    private val photoResponse: PhotoRepository,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<PhotoBaseUiState> =
        MutableStateFlow(PhotoBaseUiState())
    val uiState: StateFlow<PhotoBaseUiState>
    get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            this@PhotoBaseViewModel.preferencesRepository.storedQuery.collectLatest { storedQuery ->
                try {
                    val items = fetchGalleryItems(storedQuery)
                    _uiState.update { oldState ->  oldState.copy(
                        images = items,
                        query = storedQuery
                    )}
                } catch (ex: Exception) {
                    Log.e(ContentValues.TAG, "Failed to fetch gallery item", ex)
                }
            }
        }
    }
    fun setQuery(query: String){
        viewModelScope.launch {
            preferencesRepository.setStoredQuery(query)
        }
    }

    private suspend fun fetchGalleryItems(query: String): List<GalleryItem>{
        return if(query.isNotEmpty()){
            photoResponse.searchPhotos(query)
        }else{
            photoResponse.fetchPhotos()
        }
    }
}
