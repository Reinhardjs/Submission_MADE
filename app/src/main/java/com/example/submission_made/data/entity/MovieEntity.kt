package com.example.submission_made.data.entity

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
class MovieEntity(
    @SerializedName("original_title")
    @Expose
    override var title: String?
) : BaseEntity(title) {

    constructor() : this("")
}