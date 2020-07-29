package com.example.facegallery.data

interface DataSource {
    suspend fun getAlbums(): List<Album>
    suspend fun getPhotos(albumId: String): List<Photo>
}