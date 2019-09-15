package com.example.submission_made.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResponse<T>(
    @SerializedName("results")
    @Expose
    val results: List<T>,

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