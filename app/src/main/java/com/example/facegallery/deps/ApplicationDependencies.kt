package com.example.facegallery.deps

import com.example.facegallery.data.DataSourceResolver
import com.example.facegallery.data.repo.AlbumRepository
import com.example.facegallery.data.repo.PhotoRepository
import com.example.facegallery.remote.RemoteDataSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder

object ApplicationDependencies {
    val GSON: Gson = GsonBuilder().setPrettyPrinting().create()
    private val DATASOURCE_RESOLVER = DataSourceResolver(RemoteDataSource())
    val ALBUM_REPOSITORY = AlbumRepository(DATASOURCE_RESOLVER)
    val PHOTO_REPOSITORY = PhotoRepository(DATASOURCE_RESOLVER)
}