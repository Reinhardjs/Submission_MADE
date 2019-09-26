package com.example.submission_made.data

import com.android.volley.RequestQueue
import io.reactivex.Single
import org.json.JSONObject

interface RemoteDataSource : DataSource {
    fun getReviews(movieId: Int, queue: RequestQueue): Single<JSONObject>

    fun getTrailers(movieId: Int, queue: RequestQueue): Single<JSONObject>
}