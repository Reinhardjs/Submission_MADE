package com.example.submission_made.data

import com.example.submission_made.data.entity.FavoriteEntity
import com.example.submission_made.data.entity.MovieEntity
import com.example.submission_made.data.entity.TvEntity
import io.reactivex.Observable
import io.reactivex.Single

interface LocalDataSource : DataSource {

    fun getAllFavorite(tableName: String): Observable<List<FavoriteEntity>>

    fun getFavorite(id: Int): Single<FavoriteEntity>

    fun insertFavorite(favorite: FavoriteEntity)

    fun insertMovies(movies: List<MovieEntity>)

    fun insertTvShows(movies: List<TvEntity>)

    fun deleteAllMovies()

    fun deleteAllTvShows()

    fun deleteFavorite(id: Int)

}