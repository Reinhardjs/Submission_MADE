package com.example.submission_made.data.remote.retrofit

import RequestInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    const val API_ENDPOINT = "https://api.themoviedb.org/"

    fun <S> create(clazz: Class<S>) = Retrofit.Builder()
        .baseUrl(API_ENDPOINT)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(RequestInterceptor())
                .addNetworkInterceptor(StethoInterceptor())
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(clazz)
}