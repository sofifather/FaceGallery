package com.example.facegallery.remote

import android.os.Bundle
import com.example.facegallery.data.*
import com.example.facegallery.deps.ApplicationDependencies
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class RemoteDataSource : DataSource {
    val gson = ApplicationDependencies.GSON

    override suspend fun getAlbums(): List<Album> {
        return suspendCoroutine { cont->
            GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/albums",
                Bundle().apply {
                    putString("fields","id,picture,name")
                },
                HttpMethod.GET,
                GraphRequest.Callback {
                    cont.resume(it.rawResponse.let { jsonString ->
                        if (jsonString == null) {
                            AlbumsResponse(emptyList())
                        } else {
                            gson.fromJson(jsonString, AlbumsResponse::class.java)
                        }
                    }.data)
                }
            ).executeAsync()
        }
    }

    override suspend fun getPhotos(albumId: String): List<Photo> {
        return suspendCoroutine { cont ->
            GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/$albumId",
                Bundle().apply {
                    putString("fields", "photos{images,name,created_time}")
                },
                HttpMethod.GET,
                GraphRequest.Callback {
                    val photos = it.rawResponse.let { jsonString ->
                        if (jsonString == null) {
                            AlbumResponse(Photos(emptyList()))
                        } else {
                            gson.fromJson(jsonString, AlbumResponse::class.java)
                        }
                    }.photos?.data ?: emptyList()
                    cont.resume(photos)
                }
            ).executeAsync()
        }
    }

}