package com.example.submission_made.data.entity

class EntityConverter {

    companion object {

        fun convertToFavoriteEntity(from: BaseEntity): FavoriteEntity {
            val favoriteEntity = FavoriteEntity()
            favoriteEntity.id = from.id
            favoriteEntity.title = from.title
            favoriteEntity.tableName = from.tableName
            favoriteEntity.release_date = from.release_date
            favoriteEntity.overview = from.overview
            favoriteEntity.poster_path = from.poster_path
            favoriteEntity.backdrop_path = from.backdrop_path
            favoriteEntity.vote_count = from.vote_count
            favoriteEntity.vote_average = from.vote_average
            favoriteEntity.popularity = from.popularity

            return favoriteEntity
        }
    }

}