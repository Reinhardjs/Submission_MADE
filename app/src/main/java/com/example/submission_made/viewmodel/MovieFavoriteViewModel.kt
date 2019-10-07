package com.example.submission_made.viewmodel

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.submission_made.data.Repo
import com.example.submission_made.data.entity.FavoriteEntity
import com.example.submission_made.data.provider.Contract
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


open class MovieFavoriteViewModel @Inject
constructor(open val movieRepo: Repo, open val context: Context) : ViewModel() {

    fun getFavorite(id: Int): Single<FavoriteEntity> {
        return movieRepo.db.getFavorite(id)
    }

    fun insertFavorite(context: Context, favorite: FavoriteEntity) {
        // movieRepo.db.insertFavorite(context, favoriteEntity)

        val work = Single.create<Boolean> {

            val selectionArgs: Array<String> = arrayOf()
            val projection = arrayOf(Contract.AUTHORITY)
            val uri = Uri.parse(Contract.CONTENT_URI.toString())

            val contentResolver = context.contentResolver
            val values = ContentValues()

            values.put("id", favorite.id)
            values.put("title", favorite.title)
            values.put("tableName", favorite.tableName)
            values.put("release_date", favorite.release_date)
            values.put("overview", favorite.overview)
            values.put("poster_path", favorite.poster_path)
            values.put("backdrop_path", favorite.backdrop_path)
            values.put("vote_count", favorite.vote_count)
            values.put("vote_average", favorite.vote_average)
            values.put("popularity", favorite.popularity)

            contentResolver.insert(uri, values)

            it.onSuccess(true)

        }

        val disposable = work
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    Log.d("MYAPP", "ON SUCCESS INSERT")
                },
                {
                    Log.d("MYAPP", "ON ERROR INSERT : " + it.message)
                }
            )
    }

    fun deleteFavorite(id: Int) {
        // movieRepo.db.deleteFavorite(context, id)

        val work = Single.create<Int> {

            val selectionArgs: Array<String> = arrayOf(id.toString())
            val projection = arrayOf(Contract.AUTHORITY)
            val uri = Uri.parse(Contract.CONTENT_URI.toString())

            val contentResolver = context.contentResolver

            val affectedCount = contentResolver.delete(uri, null, selectionArgs)

            it.onSuccess(affectedCount)
        }

        val disposable = work
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    Log.d("MYAPP", "ON SUCCESS, Affected : " + it)
                },
                {
                    Log.d("MYAPP", "ON ERROR : " + it.message)
                }
            )
    }

}