package com.example.submission_made.ui.callbacks

import android.widget.ImageView
import com.example.submission_made.data.entity.MovieEntity

interface ListCallback {
    fun onItemClicked(imageView: ImageView, movieEntity: MovieEntity)
}
