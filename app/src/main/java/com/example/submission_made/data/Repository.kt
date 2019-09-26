package com.example.submission_made.data

import com.example.submission_made.data.entity.MovieEntity
import com.example.submission_made.data.local.DbData
import com.example.submission_made.data.remote.ApiData
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

object Repository {

    const val PAGE = "page"
    const val ID = "id"
    const val USER_ID = "userId"
    const val POST_ID = "postId"

    fun of(): Repo {
        return Repo(ApiData.of(MovieEntity::class), DbData.of(MovieEntity::class))
    }

    fun clearDatabase(): Completable {
        return Completable.fromCallable { DbData.clearDb() }
            .subscribeOn(Schedulers.io())
    }
}

class Repo(
    val api: RemoteDataSource,
    val db: LocalDataSource
)