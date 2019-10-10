package com.example.submission_made.data.entity

import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class BaseEntity(
    @PrimaryKey
    @SerializedName("id")
    @Expose
    open var id: Int,

    @Ignore
    open var title: String?,

    @SerializedName("tableName")
    open var tableName: String?,

    @Ignore
    open var release_date: String?,

    @SerializedName("overview")
    @Expose
    open var overview: String?,

    @SerializedName("poster_path")
    @Expose
    open var poster_path: String?,

    @SerializedName("backdrop_path")
    @Expose
    open var backdrop_path: String?,

    @SerializedName("vote_count")
    @Expose
    open var vote_count: Int?,

    @SerializedName("vote_average")
    @Expose
    open var vote_average: Double?,

    @SerializedName("popularity")
    @Expose
    open var popularity: Double?
) : Serializable {

    constructor(title: String?) : this(-1, title, "","", "", "", "", 0, 0.0, 0.0)

    fun getPosterImageUrl() = "https://image.tmdb.org/t/p/w342" + this.poster_path

    fun getBackdropImageUrl() = "https://image.tmdb.org/t/p/w1280" + this.backdrop_path

}