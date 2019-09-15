package com.example.submission_made.data

import com.example.submission_made.data.entity.MovieEntity
import com.example.submission_made.data.entity.TvEntity
import io.reactivex.Observable

interface DataSource {

    fun getMovies(category: String): Observable<List<MovieEntity>>

    fun getTvShows(category: String): Observable<List<TvEntity>>

}