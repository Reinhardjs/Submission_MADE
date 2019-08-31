package com.example.submission_made.data.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieData(
    var id: Int,
    var title: String,
    var overview: String,
    var release_date: String,
    var poster_path: String,
    var backdrop_path: String,
    var vote_count: Int,
    var vote_average: Double,
    var popularity: Double): Parcelable {

    constructor() : this(-1, "", "", "", "", "", 0, 0.0, 0.0)

    fun getPosterImageUrl() = "https://image.tmdb.org/t/p/w342"+ this.poster_path;

    fun getBackdropImageUrl() = "https://image.tmdb.org/t/p/w342"+ this.backdrop_path;

}