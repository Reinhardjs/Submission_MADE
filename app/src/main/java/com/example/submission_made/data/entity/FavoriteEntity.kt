package com.example.submission_made.data.entity

import androidx.room.Entity

@Entity(tableName = "favorites")
class FavoriteEntity(
    override var title: String?
) : BaseEntity(title) {

    constructor() : this("")

}