package com.example.submission_made.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    var id: Int,
    var title: String,
    var overview: String,
    var release_date: String,

    @SerializedName("poster_path")
    @Expose
    var poster_path: String,

    @SerializedName("backdrop_path")
    @Expose
    var backdrop_path: String,

    @SerializedName("vote_count")
    @Expose
    var vote_count: Int,

    @SerializedName("vote_average")
    @Expose
    var vote_average: Double,

    @SerializedName("popularity")
    @Expose
    var popularity: Double): Parcelable {

    constructor() : this(-1, "", "", "", "", "", 0, 0.0, 0.0)

    fun getPosterImageUrl() = "https://image.tmdb.org/t/p/w342"+ this.poster_path;

    fun getBackdropImageUrl() = "https://image.tmdb.org/t/p/w342"+ this.backdrop_path;

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return if (other is MovieEntity) other.id == id else false
    }
}