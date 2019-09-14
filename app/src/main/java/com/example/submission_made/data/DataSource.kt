package com.example.submission_made.data

import com.example.submission_made.data.entity.MovieEntity
import io.reactivex.Observable

interface DataSource {

    fun getAll(type: String, category: String): Observable<List<MovieEntity>>

}