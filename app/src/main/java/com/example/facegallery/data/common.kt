package com.example.facegallery.data

import java.util.*

data class AlbumsResponse(val data: List<Album>)

data class Album (
    val created_time: Date,
    val name: String,
    val id: String,
    val picture: AlbumPicture
)

data class AlbumPicture(
    val data: PictureData
)

data class PictureData(
    val is_silhouette: Boolean,
    val url: String
)

data class AlbumResponse(val photos: Photos?)

data class Photos(val data: List<Photo>?)

data class Photo(val created_time: Date?, val name: String?, val id: String, val images: List<Image>)

data class Image(val height: Int, val width: Int, val source: String)

