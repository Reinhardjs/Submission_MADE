package com.example.submission_made.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.ConnectivityManager
import android.net.Uri
import com.example.submission_made.data.entity.FavoriteEntity


object Utility {

    fun favoriteCursorToListConverter(cursor: Cursor): List<FavoriteEntity> {
        var movieList = mutableListOf<FavoriteEntity>()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(Constants.COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_TITLE))
            val tableName =
                cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_TABLE_NAME))
            val release_date =
                cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_TABLE_NAME))
            val overview =
                cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_OVERVIEW))
            val poster_path =
                cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_POSTER_PATH))
            val backdrop_path =
                cursor.getString(cursor.getColumnIndexOrThrow(Constants.COLUMN_BACKDROP_PATH))
            val vote_count =
                cursor.getInt(cursor.getColumnIndexOrThrow(Constants.COLUMN_VOTE_COUNT))
            val vote_average =
                cursor.getDouble(cursor.getColumnIndexOrThrow(Constants.COLUMN_VOTE_AVERAGE))
            val popularity =
                cursor.getDouble(cursor.getColumnIndexOrThrow(Constants.COLUMN_POPULARITY))

            val favEntity = FavoriteEntity("")
            favEntity.id = id
            favEntity.title = title
            favEntity.tableName = tableName
            favEntity.release_date = release_date
            favEntity.overview = overview
            favEntity.poster_path = poster_path
            favEntity.backdrop_path = backdrop_path
            favEntity.vote_count = vote_count
            favEntity.vote_average = vote_average
            favEntity.popularity = popularity

            movieList.add(favEntity)
        }

        return movieList
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun isAppInstalled(uri: String, context: Context): Boolean {
        val pm = context.packageManager
        var installed = false
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
            installed = true
        } catch (e: PackageManager.NameNotFoundException) {
            installed = false
        }

        return installed
    }

    fun viewUrl(url: String, context: Context) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun watchYoutubeVideo(id: String, context: Context) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            if (isAppInstalled("com.google.android.youtube", context)) {
                intent.setClassName(
                    "com.google.android.youtube",
                    "com.google.android.youtube.WatchActivity"
                )
            }
            context.startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=$id")
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

    }
}