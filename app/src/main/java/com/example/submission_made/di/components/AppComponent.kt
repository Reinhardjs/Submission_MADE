package com.example.submission_made.di.components

import com.example.submission_made.MovieApp
import com.example.submission_made.di.builder.ActivityBuilderModule
import com.example.submission_made.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class, ActivityBuilderModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MovieApp): Builder

        fun build(): AppComponent
    }

    fun inject(movieApp: MovieApp)
}
