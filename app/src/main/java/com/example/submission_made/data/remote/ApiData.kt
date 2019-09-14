package com.example.submission_made.data.remote

import com.example.submission_made.data.DataSource
import com.example.submission_made.data.entity.MovieEntity
import kotlin.reflect.KClass

object ApiData {

    fun of(clazz: KClass<*>): DataSource {
        return when (clazz) {
            MovieEntity::class -> MovieApiData()
            else -> throw IllegalArgumentException("Unsupported data type")
        }
    }
}