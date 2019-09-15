package com.example.submission_made.di.module

import android.content.Context
import com.example.submission_made.MovieApp
import com.example.submission_made.data.AppDatabase
import com.example.submission_made.data.Repo
import com.example.submission_made.data.Repository
import com.example.submission_made.data.entity.BaseEntity
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.example.submission_made.data.remote.retrofit.JsonDeserializerWithInheritance
import com.google.gson.GsonBuilder

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(context: MovieApp): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideMovieRepo(): Repo {
        return Repository.of()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val builder = GsonBuilder()
        builder
            .registerTypeAdapter(
                BaseEntity::class.java,
                JsonDeserializerWithInheritance<BaseEntity>()
            )
        val gson = builder.create()

        return gson
    }
}
