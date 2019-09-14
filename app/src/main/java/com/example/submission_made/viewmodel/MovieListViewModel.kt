package com.example.submission_made.viewmodel

import androidx.lifecycle.ViewModel
import com.example.submission_made.data.Repo
import com.example.submission_made.data.entity.MovieEntity
import io.reactivex.Observable
import javax.inject.Inject

class MovieListViewModel @Inject
constructor(val movieRepo: Repo) : ViewModel() {

    fun getMovies(): Observable<List<MovieEntity>> {
        return movieRepo.api.getAll("movie", "popular")
    }

    fun getTvShows(): Observable<List<MovieEntity>> {
        return movieRepo.api.getAll("tv", "popular")
    }

}