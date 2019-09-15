package com.example.submission_made.data.local


import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.submission_made.data.entity.MovieEntity
import com.example.submission_made.data.entity.TvEntity
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
abstract class MovieDao {

    @Query("SELECT * FROM movies")
    abstract fun getMovies(): Observable<List<MovieEntity>>

    @Query("SELECT * FROM tvshows")
    abstract fun getTvShows(): Observable<List<TvEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(movies: List<MovieEntity>)

    @Delete
    abstract fun deleteAll(movies: List<MovieEntity>)

    @Query("DELETE FROM movies")
    abstract fun deleteAll()

    @RawQuery
    abstract fun rawQuery(query: SupportSQLiteQuery): Maybe<List<MovieEntity>>

}