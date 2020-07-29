package com.example.facegallery.data

import com.example.facegallery.deps.ApplicationDependencies
import com.example.facegallery.remote.RemoteDataSource
import java.lang.UnsupportedOperationException

class DataSourceResolver(private val remoteDataSource: RemoteDataSource) {
    fun resolveDataSource(constraint: Constraint = Constraint.REMOTE): DataSource {
        return when(constraint) {
            Constraint.REMOTE -> remoteDataSource
            else -> throw UnsupportedOperationException()
        }
    }
}

enum class Constraint {
    REMOTE,
    IN_MEMORY,
    CACHE
}