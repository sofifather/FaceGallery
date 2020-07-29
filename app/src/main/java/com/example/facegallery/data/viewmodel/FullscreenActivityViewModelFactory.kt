package com.example.facegallery.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.facegallery.deps.ApplicationDependencies

class FullscreenActivityViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FullscreenActivityViewModel::class.java)) {
            return FullscreenActivityViewModel(ApplicationDependencies.PHOTO_REPOSITORY) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}