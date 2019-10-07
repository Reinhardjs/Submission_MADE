package com.example.submission_made.data.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Intent
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.util.Log
import com.example.submission_made.MovieApp
import com.example.submission_made.data.Repo
import com.example.submission_made.data.Repository
import com.example.submission_made.data.entity.BaseEntity
import com.example.submission_made.data.entity.EntityConverter
import com.example.submission_made.data.local.DbData
import java.lang.Integer.parseInt

class MovieProvider : ContentProvider() {

    // https://blog.mindorks.com/android-content-provider-in-kotlin

    lateinit var mRepo: Repo
    private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    override fun onCreate(): Boolean {
        MovieApp.appContext = context
        initializeUriMatching()
        mRepo = Repository.of()
        return true
    }

    private fun initializeUriMatching() {
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH + "/#", 1)
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH, 0)
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor {

        var id = -1
        var tableName = ""

        when (sUriMatcher.match(uri)) {
            0 -> {

                id = Contract.ALL_ITEMS
                if (selectionArgs != null) {
                    tableName = selectionArgs.get(1)
                }
            }

            1 -> id = parseInt(uri.lastPathSegment!!)

            UriMatcher.NO_MATCH -> {
                id = -1
            }

            else -> {
                id = -1
            }
        }

        Log.d("MYAPP", "ON QUERY PROVIDER")

        return populateCursor(id, tableName)
    }

    private fun populateCursor(id: Int, tableName: String): Cursor {
        val cursor = MatrixCursor(arrayOf(Contract.CONTENT_PATH))
        if (id == Contract.ALL_ITEMS) {
            return mRepo.db.getAllProviderFavorite(tableName)
        } else if (id >= 0) {
            return mRepo.db.getProviderFavorite(id)
        }
        return cursor
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues): Uri {
        Log.d("MYAPP", "PROVIDER ON INSERT")

        val id = values.getAsInteger("id")
        val title = values.getAsString("title")
        val tableName = values.getAsString("tableName")
        val release_date = values.getAsString("release_date")
        val overview = values.getAsString("overview")
        val poster_path = values.getAsString("poster_path")
        val backdrop_path = values.getAsString("backdrop_path")
        val vote_count = values.getAsInteger("vote_count")
        val vote_average = values.getAsDouble("vote_average")
        val popularity = values.getAsDouble("popularity")

        val baseEntity = BaseEntity(
            id,
            title,
            tableName,
            release_date,
            overview,
            poster_path,
            backdrop_path,
            vote_count,
            vote_average,
            popularity
        )

        val favEntity = EntityConverter.convertToFavoriteEntity(baseEntity)

        DbData.db.getMovieDao().insertFavorite(favEntity)

        val intent = Intent("com.example.submission_made.UPDATE_ACTION")
        context.sendBroadcast(intent)

        return Uri.EMPTY
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        Log.d("MYAPP", "PROVIDER ON DELETE")

        val id = parseInt(selectionArgs!![0])
        // mRepo.db.deleteFavorite(context, id)
        DbData.db.getMovieDao().deleteFavorite(id)

        val intent = Intent("com.example.submission_made.UPDATE_ACTION")
        context.sendBroadcast(intent)

        return 1
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }

}