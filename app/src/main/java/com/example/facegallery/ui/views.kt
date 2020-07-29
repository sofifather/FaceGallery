package com.example.facegallery.ui

import java.util.*

data class AlbumView(val albumId: String, val miniatureWebPath: String, val name: String)
data class PhotoView(val miniatureWebPath: String, val name: String?, val date: Date?)