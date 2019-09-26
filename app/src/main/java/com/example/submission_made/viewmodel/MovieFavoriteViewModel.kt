package com.example.submission_made.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.submission_made.data.Repo
import com.example.submission_made.data.entity.FavoriteEntity
import io.reactivex.Single
import javax.inject.Inject


open class MovieFavoriteViewModel @Inject
constructor(open val movieRepo: Repo, open val context: Context) : ViewModel() {

    fun getFavorite(id: Int): Single<FavoriteEntity> {
        return movieRepo.db.getFavorite(id)
    }

    fun insertFavorite(favoriteEntity: FavoriteEntity) {
        movieRepo.db.insertFavorite(favoriteEntity)
    }

    fun deleteFavorite(id: Int) {
        movieRepo.db.deleteFavorite(id)
    }

}