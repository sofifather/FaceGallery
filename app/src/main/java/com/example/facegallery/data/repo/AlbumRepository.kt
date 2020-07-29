package com.example.facegallery.data.repo

import com.example.facegallery.data.DataSourceResolver

class AlbumRepository(private val dataSourceResolver: DataSourceResolver) {
    suspend fun getAlbums() = dataSourceResolver.resolveDataSource().getAlbums()
}