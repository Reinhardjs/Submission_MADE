package com.example.submission_made.viewmodel

import androidx.lifecycle.ViewModel
import com.example.submission_made.data.Repo
import com.example.submission_made.data.entity.MovieEntity
import com.example.submission_made.data.entity.TvEntity
import io.reactivex.Observable
import javax.inject.Inject

class MovieListViewModel @Inject
constructor(val movieRepo: Repo) : ViewModel() {

    fun getMovies(): Observable<List<MovieEntity>> {
        // handle ambil data offline & online nya di sini
        return movieRepo.api.getMovies("popular")
    }

    fun getTvShows(): Observable<List<TvEntity>> {
        // handle ambil data offline & online nya di sini
        return movieRepo.api.getTvShows("popular")
    }

}