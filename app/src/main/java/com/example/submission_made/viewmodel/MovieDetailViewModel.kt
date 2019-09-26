package com.example.submission_made.viewmodel

import android.content.Context
import com.android.volley.RequestQueue
import com.example.submission_made.data.Repo
import io.reactivex.Single
import org.json.JSONObject
import javax.inject.Inject

class MovieDetailViewModel @Inject
constructor(override val movieRepo: Repo, override val context: Context) :
    MovieFavoriteViewModel(movieRepo, context) {

    fun getReviews(movieId: Int, queue: RequestQueue): Single<JSONObject> {
        return movieRepo.api.getReviews(movieId, queue)
    }

    fun getTrailers(movieId: Int, queue: RequestQueue): Single<JSONObject> {
        return movieRepo.api.getTrailers(movieId, queue)
    }

}