package com.example.submission_made.data.local

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.submission_made.data.entity.FavoriteEntity
import com.example.submission_made.data.entity.MovieEntity
import com.example.submission_made.data.entity.TvEntity
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

@Dao
abstract class MovieDao {

    @Query("SELECT * FROM movies")
    abstract fun getMovies(): Observable<List<MovieEntity>>

    @Query("SELECT * FROM tvshows")
    abstract fun getTvShows(): Observable<List<TvEntity>>

    @Query("SELECT * FROM favorites WHERE id=:id")
    abstract fun getFavorite(id: Int): Single<FavoriteEntity>

    @Query("SELECT * FROM favorites WHERE tableName=:tableName")
    abstract fun getFavorites(tableName: String): Observable<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertFavorite(favorite: FavoriteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTvShows(tvShows: List<TvEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertFavorites(favorites: List<FavoriteEntity>)

    @Delete
    abstract fun deleteMovies(movies: List<MovieEntity>)

    @Delete
    abstract fun deleteTvShows(tvShows: List<TvEntity>)

    @Query("DELETE FROM movies")
    abstract fun deleteAllMovies()

    @Query("DELETE FROM tvshows")
    abstract fun deleteAllTvShows()

    @Query("DELETE FROM favorites")
    abstract fun deleteAllFavorites()

    @Query("DELETE FROM favorites WHERE id=:id")
    abstract fun deleteFavorite(id: Int)

    @RawQuery
    abstract fun rawQuery(query: SupportSQLiteQuery): Maybe<List<MovieEntity>>

}