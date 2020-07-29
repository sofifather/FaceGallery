package com.example.facegallery.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.facegallery.data.repo.AlbumRepository
import com.example.facegallery.data.Album
import kotlinx.coroutines.launch

class AlbumListFragmentViewModel(private val repository: AlbumRepository) : ViewModel() {

    private var _albumData = MutableLiveData<List<Album>>().apply {
        fetchAlbums()
    }

    val albumData: LiveData<List<Album>> = _albumData

    private fun fetchAlbums() {
        viewModelScope.launch {
            repository
                .getAlbums()
                .apply {
                    _albumData.postValue(this)
                }
        }
    }

}