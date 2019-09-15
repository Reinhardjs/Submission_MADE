package com.example.submission_made.ui.callbacks

import android.widget.ImageView

interface ListCallback<T> {
    fun onItemClicked(imageView: ImageView, movieEntity: T)
}
