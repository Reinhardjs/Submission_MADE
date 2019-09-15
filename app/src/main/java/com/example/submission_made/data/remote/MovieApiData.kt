package com.example.submission_made.data.remote

import com.example.submission_made.data.DataSource
import com.example.submission_made.data.entity.MovieEntity
import com.example.submission_made.data.entity.TvEntity
import com.example.submission_made.data.remote.retrofit.RetrofitBuilder
import io.reactivex.Observable

class MovieApiData : DataSource {

    private val movieService: MovieService =
        RetrofitBuilder.create(MovieService::class.java) as MovieService

    override fun getMovies(category: String): Observable<List<MovieEntity>> {
        // Melakukan convert observable dari Response menjadi List<MovieEntity> langsung
        return movieService.getMovies(category)
            .flatMap { response -> Observable.fromIterable(listOf(response.results)) }
    }

    override fun getTvShows(category: String): Observable<List<TvEntity>> {
        // Melakukan convert observable dari Response menjadi List<TvEntity> langsung
        return movieService.getTvShows(category)
            .flatMap { response -> Observable.fromIterable(listOf(response.results)) }
    }

}