package com.example.submission_made.data.provider

import android.net.Uri

class Contract  {
    companion object {
        val AUTHORITY  = "com.example.submission_made.provider"
        val CONTENT_PATH = "favorites"
        val CONTENT_URI = Uri.parse("content://$AUTHORITY/$CONTENT_PATH")
        val ALL_ITEMS = -2
    }
}