package com.example.submission_made.ui.callbacks

import android.widget.ImageView
import com.example.submission_made.data.pojo.MovieData

interface ListCallback {
    fun onItemClicked(imageView: ImageView, movieData: MovieData)
}
