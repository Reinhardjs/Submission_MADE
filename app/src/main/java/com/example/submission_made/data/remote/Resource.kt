package com.example.submission_made.data.remote

import com.example.submission_made.data.remote.Status.*

class Resource<T>(var status: Status?, val data: T?, val message: String?) {
    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}