package com.example.submission_made.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submission_made.data.pojo.MovieData
import com.example.submission_made.data.remote.Resource
import com.example.submission_made.data.remote.repository.MovieRepository
import java.util.ArrayList

import javax.inject.Inject

class MovieListViewModel @Inject
constructor(val movieRepository: MovieRepository) : ViewModel() {

    fun getMovies(): LiveData<Resource<ArrayList<MovieData>>>? {
        return movieRepository.loadMovies()
    }

    fun getTvShows(): LiveData<Resource<ArrayList<MovieData>>>? {
        return movieRepository.loadTvShows()
    }

}