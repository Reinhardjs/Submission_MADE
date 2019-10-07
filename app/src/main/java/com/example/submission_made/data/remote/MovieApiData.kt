package com.example.submission_made.data.remote

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.submission_made.data.RemoteDataSource
import com.example.submission_made.data.entity.BaseEntity
import com.example.submission_made.data.entity.MovieEntity
import com.example.submission_made.data.entity.TvEntity
import com.example.submission_made.data.remote.retrofit.RetrofitBuilder
import io.reactivex.Observable
import io.reactivex.Single
import org.json.JSONObject

class MovieApiData : RemoteDataSource {

    private val movieService: MovieService =
        RetrofitBuilder.create(MovieService::class.java) as MovieService

    override fun getMovies(category: String): Observable<List<MovieEntity>> {
        // Melakukan convert observable dari Response menjadi List<MovieEntity> langsung
        return movieService.getMovies(category)
            .flatMap { response ->
                Observable.fromIterable(
                    listOf(
                        setTableName(
                            "movies",
                            response.results
                        )
                    )
                )
            }
    }

    override fun getTvShows(category: String): Observable<List<TvEntity>> {
        // Melakukan convert observable dari Response menjadi List<TvEntity> langsung
        return movieService.getTvShows(category)
            .flatMap { response ->
                Observable.fromIterable(
                    listOf(
                        setTableName(
                            "tvshows",
                            response.results
                        )
                    )
                )
            }
    }

    override fun searchMovies(language: String, query: String): Observable<List<MovieEntity>> {
        return movieService.searchMovies(language, query)
            .flatMap { response ->
                Observable.fromIterable(
                    listOf(
                        setTableName(
                            "movies",
                            response.results
                        )
                    )
                )
            }
    }

    override fun searchTvShows(language: String, query: String): Observable<List<TvEntity>> {
        return movieService.searchTvShows(language, query)
            .flatMap { response ->
                Observable.fromIterable(
                    listOf(
                        setTableName(
                            "tvshows",
                            response.results
                        )
                    )
                )
            }
    }

    override fun getTodayReleases(gte: String, lte: String): Observable<List<MovieEntity>> {
        return movieService.getTodayReleases(gte, lte)
            .flatMap { response ->
                Observable.fromIterable(
                    listOf(
                        setTableName(
                            "movies",
                            response.results
                        )
                    )
                )
            }
    }

    fun <T : BaseEntity> setTableName(tableName: String, results: List<T>): List<T> {
        for (item in results) {
            item.tableName = tableName
        }

        return results
    }


    // https://aarcoraci.wordpress.com/2017/08/21/improving-our-volley-solution-using-rxjava-rxandroid/

    override fun getReviews(movieId: Int, queue: RequestQueue): Single<JSONObject> {
        return Single.create {

            val url = "https://api.themoviedb.org/3/movie/" +
                    movieId +
                    "/reviews?" +
                    "api_key=" + ApiConstants.API_KEY + "&language=en-US&page=1"

            val getRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                Response.Listener { response ->
                    // display response
                    // Log.d("Response", response.toString());

                    it.onSuccess(response)

                },
                Response.ErrorListener {
                    it.printStackTrace()
                }
            )

            queue.add(getRequest)

        }
    }

    override fun getTrailers(movieId: Int, queue: RequestQueue): Single<JSONObject> {
        return Single.create {

            val url = "https://api.themoviedb.org/3/movie/" +
                    movieId +
                    "/videos?" +
                    "api_key=" + ApiConstants.API_KEY + "&language=en-US&page=1"

            val getRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                Response.Listener { response ->

                    it.onSuccess(response)

                },
                Response.ErrorListener {

                    it.printStackTrace()

                }
            )

            queue.add(getRequest)

        }
    }

}