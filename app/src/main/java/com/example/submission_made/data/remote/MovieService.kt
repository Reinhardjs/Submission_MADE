package com.example.submission_made.data.remote

import com.example.submission_made.data.remote.response.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    // movie
    // ex https://api.themoviedb.org/3/movie/popular?api_key=b2e10e07882544ff0655c3a7fe130806
    @GET("/3/{type}/{category}")
    fun getMovies(
        @Path("type") type: String, @Path("category") category: String): Observable<MovieResponse>

}