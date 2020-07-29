package com.example.facegallery.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.facegallery.deps.ApplicationDependencies

class AlbumListFragmentViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumListFragmentViewModel::class.java)) {
            return AlbumListFragmentViewModel(ApplicationDependencies.ALBUM_REPOSITORY) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}