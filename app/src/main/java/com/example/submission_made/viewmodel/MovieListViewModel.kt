package com.example.submission_made.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.submission_made.data.Repo
import com.example.submission_made.data.entity.FavoriteEntity
import com.example.submission_made.data.entity.MovieEntity
import com.example.submission_made.data.entity.TvEntity
import com.example.submission_made.utils.Utility
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieListViewModel @Inject
constructor(val movieRepo: Repo, val context: Context) : ViewModel() {

    fun getFavorites(tableName: String): Observable<List<FavoriteEntity>>{
        return movieRepo.db.getAllFavorite(tableName)
    }

    fun searchMovies(language: String, query: String): Observable<List<MovieEntity>> {
        return movieRepo.api.searchMovies(language, query)
    }

    fun searchTvShows(language: String, query: String): Observable<List<TvEntity>> {
        return movieRepo.api.searchTvShows(language, query)
    }

    fun getMovies(): Observable<List<MovieEntity>> {
        // handle ambil data offline & online nya di sini

        if (Utility.isNetworkAvailable(context)){

            val observable: Observable<List<MovieEntity>> = movieRepo.api.getMovies("popular")

            var disposable: Disposable? = observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {

                    movieRepo.db.deleteAllMovies()
                    movieRepo.db.insertMovies(it)

                }

            return observable

        } else {
            return movieRepo.db.getMovies("popular")
        }
    }

    fun getTvShows(): Observable<List<TvEntity>> {
        // handle ambil data offline & online nya di sini

        if (Utility.isNetworkAvailable(context)) {
            val observable: Observable<List<TvEntity>> = movieRepo.api.getTvShows("popular")

            var disposable: Disposable? = observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {

                    movieRepo.db.deleteAllTvShows()
                    movieRepo.db.insertTvShows(it)

                }

            return observable

        } else {
            return movieRepo.db.getTvShows("popular")
        }

    }

}