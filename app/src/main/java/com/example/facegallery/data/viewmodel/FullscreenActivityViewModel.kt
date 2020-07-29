package com.example.facegallery.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.facegallery.data.Photo
import com.example.facegallery.data.repo.PhotoRepository

class FullscreenActivityViewModel(private val photoRepository: PhotoRepository) : ViewModel() {
    private var _photos = MutableLiveData<List<Photo>>().apply {
        value = photoRepository.getRecentPhotos()
    }

    val photos: LiveData<List<Photo>> = _photos
}