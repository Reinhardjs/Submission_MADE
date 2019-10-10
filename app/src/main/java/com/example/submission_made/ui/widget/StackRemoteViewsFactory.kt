package com.example.submission_made.ui.widget

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf
import com.example.submission_made.R
import com.example.submission_made.data.entity.FavoriteEntity
import com.example.submission_made.data.provider.Contract
import com.example.submission_made.utils.Utility
import com.squareup.picasso.Picasso

internal class StackRemoteViewsFactory(private val mContext: Context) : RemoteViewsService.RemoteViewsFactory {

    private var favoriteEntities = ArrayList<FavoriteEntity>()

    override fun onCreate() {
        Log.d("MYAPP", "TERJADI ONCREATE")
    }

    override fun onDataSetChanged() {
        //Ini berfungsi untuk melakukan refresh saat terjadi perubahan.

        favoriteEntities = ArrayList()

        val selectionArgs: Array<String> = arrayOf("-123", "movies&tvshows")
        val projection = arrayOf(Contract.AUTHORITY)

        val cursor = mContext.contentResolver.query(
                Uri.parse(Contract.CONTENT_URI.toString()), projection, null,
                selectionArgs, null
        )

        if (cursor != null) {

            val movieList = Utility.favoriteCursorToListConverter(cursor)
            cursor.close()
            favoriteEntities.addAll(movieList)

        } else {

            Log.d("MYAPP", "CURSOR NULL")

        }

        Log.d("MYAPP", "TERJADI PERUBAHAN")
    }

    override fun onDestroy() {
    }

    override fun getCount(): Int = favoriteEntities.size

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(mContext.packageName, R.layout.widget_item)

        if (favoriteEntities.size > 0) {
            Log.d("MYAPP", "VIEW AT : " + position + " -> " + favoriteEntities[position].title)
            val bitmap =
                Picasso.with(mContext).load(favoriteEntities[position].getBackdropImageUrl()).get()
            rv.setImageViewBitmap(R.id.imageView, bitmap)
            rv.setTextViewText(R.id.textTitle, favoriteEntities[position].title)
        }

        val extras = bundleOf(
                FavoriteWidget.EXTRA_ITEM to position
        )
        val fillInIntent = Intent()
        fillInIntent.putExtras(extras)
        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent)
        return rv
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(i: Int): Long = 0

    override fun hasStableIds(): Boolean = false

}