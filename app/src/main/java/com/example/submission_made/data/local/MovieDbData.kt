package com.example.submission_made.data.local

import com.example.submission_made.data.DataSource
import com.example.submission_made.data.entity.MovieEntity
import io.reactivex.Observable

class MovieDbData(val dao: MovieDao) : DataSource {

    override fun getAll(type: String, category: String): Observable<List<MovieEntity>> {
        return dao.getAll()
    }

}