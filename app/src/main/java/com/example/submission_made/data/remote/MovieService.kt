package com.example.submission_made.data.remote

import com.example.submission_made.data.entity.MovieEntity
import com.example.submission_made.data.entity.TvEntity
import com.example.submission_made.data.remote.response.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    // example : https://api.themoviedb.org/3/movie/popular?api_key=

    @GET("/3/movie/{category}")
    fun getMovies(
        @Path("category") category: String
    ): Observable<MovieResponse<MovieEntity>>

    @GET("/3/tv/{category}")
    fun getTvShows(
        @Path("category") category: String
    ): Observable<MovieResponse<TvEntity>>

    @GET("/3/search/movie")
    fun searchMovies(
        @Query("language") language: String,
        @Query("query") query: String
    ): Observable<MovieResponse<MovieEntity>>

    @GET("/3/search/tv")
    fun searchTvShows(
        @Query("language") language: String,
        @Query("query") query: String
    ): Observable<MovieResponse<TvEntity>>

    @GET("/3/discover/movie")
    fun getTodayReleases(
        @Query("primary_release_date.gte") gte: String,
        @Query("primary_release_date.lte") lte: String
    ): Observable<MovieResponse<MovieEntity>>

}