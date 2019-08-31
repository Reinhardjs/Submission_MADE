package com.example.submission_made.di.module

import android.content.Context
import com.example.submission_made.MovieApp
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
}
