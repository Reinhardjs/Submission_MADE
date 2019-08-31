package com.example.submission_made.di.builder

import com.example.submission_made.ui.fragment.MovieListFragment
import com.example.submission_made.ui.fragment.TvShowListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMoviesListFragment(): MovieListFragment

    @ContributesAndroidInjector
    internal abstract fun contributeTvShowListFragment(): TvShowListFragment

}
