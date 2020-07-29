package com.example.facegallery.util

import java.text.SimpleDateFormat
import java.util.*

val dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)

fun Date.toStringDate(): String {
    return dateFormatter.format(this)
}