package com.example.facegallery.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.facegallery.deps.ApplicationDependencies

class PhotoListFragmentViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoListFragmentViewModel::class.java)) {
            return PhotoListFragmentViewModel(ApplicationDependencies.PHOTO_REPOSITORY) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}