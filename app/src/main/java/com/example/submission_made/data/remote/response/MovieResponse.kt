package com.example.submission_made.data.remote.response

import com.example.submission_made.data.entity.MovieEntity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    @Expose
    val results: List<MovieEntity>,

    @SerializedName("page")
    @Expose
    var page: Int,

    @SerializedName("total_results")
    @Expose
    var total_results: Int,

    @SerializedName("total_pages")
    @Expose
    var total_pages: Int
)