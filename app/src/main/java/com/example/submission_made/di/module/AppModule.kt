package com.example.submission_made.di.module

import android.content.Context
import com.example.submission_made.MovieApp
import com.example.submission_made.data.AppDatabase
import com.example.submission_made.data.Repo
import com.example.submission_made.data.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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
}
