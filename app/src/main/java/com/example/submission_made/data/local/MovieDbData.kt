package com.example.submission_made.data.local

import com.example.submission_made.data.LocalDataSource
import com.example.submission_made.data.entity.FavoriteEntity
import com.example.submission_made.data.entity.MovieEntity
import com.example.submission_made.data.entity.TvEntity
import io.reactivex.Observable
import io.reactivex.Single

class MovieDbData(val dao: MovieDao) : LocalDataSource {

    // Favorite CRUD
    // ################################################################################

    override fun getFavorite(id: Int): Single<FavoriteEntity> {
        return dao.getFavorite(id)
    }

    override fun getAllFavorite(tableName: String): Observable<List<FavoriteEntity>> {
        return dao.getFavorites(tableName)
    }

    override fun insertFavorite(favorite: FavoriteEntity) {
        dao.insertFavorite(favorite)
    }

    override fun deleteFavorite(id: Int) {
        dao.deleteFavorite(id)
    }

    // ################################################################################


    override fun deleteAllMovies() {
        dao.deleteAllMovies()
    }

    override fun deleteAllTvShows() {
        dao.deleteAllTvShows()
    }

    override fun insertTvShows(movies: List<TvEntity>) {
        dao.insertTvShows(movies)
    }

    override fun insertMovies(movies: List<MovieEntity>) {
        dao.insertMovies(movies)
    }

    override fun getMovies(category: String): Observable<List<MovieEntity>> {
        return dao.getMovies()
    }

    override fun getTvShows(category: String): Observable<List<TvEntity>> {
        // return dao.getTvShows()
        return dao.getTvShows()
    }

}