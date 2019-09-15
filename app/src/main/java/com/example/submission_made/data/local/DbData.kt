package com.example.submission_made.data.local

import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.submission_made.MovieApp
import com.example.submission_made.data.AppDatabase
import com.example.submission_made.data.DataSource
import com.example.submission_made.data.entity.MovieEntity
import com.example.submission_made.data.entity.TvEntity
import kotlin.reflect.KClass

object DbData {

    val db: AppDatabase by lazy { AppDatabase.getInstance(MovieApp.getContext()) }

    fun of(clazz: KClass<*>): DataSource {
        return when (clazz) {
            MovieEntity::class -> MovieDbData(db.getMovieDao())
            TvEntity::class -> MovieDbData(db.getMovieDao())
            else -> throw IllegalArgumentException("Unsupported data type")
        }
    }

    fun clearDb() {
        db.clearAllTables()
    }

    // util method for converting PARAMS MAP to sql QUERY with WHERE keyword
    fun sqlWhere(table: String, params: Map<String, String>): SimpleSQLiteQuery {
        var query = "SELECT * FROM $table"
        params.keys.forEachIndexed { i, s ->
            query += if (i == 0) " WHERE" else " AND"
            query += " $s = ?"
        }

        val args = params.values.toTypedArray()
        return SimpleSQLiteQuery(query, args)
    }

}