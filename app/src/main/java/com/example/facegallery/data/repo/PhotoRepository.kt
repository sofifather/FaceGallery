package com.example.facegallery.data.repo

import com.example.facegallery.data.DataSourceResolver
import com.example.facegallery.data.Photo

class PhotoRepository(private val dataSourceResolver: DataSourceResolver) {

    private var _recentPhotos:List<Photo>? = null

    suspend fun getPhotos(albumId: String): List<Photo> {
        return dataSourceResolver.resolveDataSource().getPhotos(albumId).apply {
            _recentPhotos = this
        }
    }

    fun getRecentPhotos(): List<Photo> {
        return _recentPhotos ?: emptyList()
    }
}