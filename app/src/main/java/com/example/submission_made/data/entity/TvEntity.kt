package com.example.submission_made.data.entity

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tvshows")
data class TvEntity(
    @SerializedName("original_name")
    @Expose
    override var title: String,

    @SerializedName("first_air_date")
    @Expose
    override var release_date: String
) : BaseEntity(title) {

    constructor() : this("", "")
}