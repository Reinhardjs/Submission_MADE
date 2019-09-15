package com.example.submission_made.data.local

import com.example.submission_made.data.DataSource
import com.example.submission_made.data.entity.MovieEntity
import com.example.submission_made.data.entity.TvEntity
import io.reactivex.Observable

class MovieDbData(val dao: MovieDao) : DataSource {

    override fun getMovies(category: String): Observable<List<MovieEntity>> {
        // return dao.getMovies()
        TODO("not implemented")
    }

    override fun getTvShows(category: String): Observable<List<TvEntity>> {
        // return dao.getTvShows()
        TODO("not implemented")
    }

}