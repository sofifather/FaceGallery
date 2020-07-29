package com.example.facegallery.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facegallery.data.repo.PhotoRepository
import com.example.facegallery.data.Photo
import kotlinx.coroutines.launch

class PhotoListFragmentViewModel(private val repository: PhotoRepository) : ViewModel() {
    private val _photos = MutableLiveData<List<Photo>>()

    val photos: LiveData<List<Photo>> = _photos

    fun fetchPhotos(albumId: String) {
        viewModelScope.launch {
            repository.getPhotos(albumId).let { list ->
                _photos.postValue(list)
            }
        }
    }
}