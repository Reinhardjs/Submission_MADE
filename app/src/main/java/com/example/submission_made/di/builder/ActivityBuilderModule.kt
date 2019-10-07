package com.example.submission_made.di.builder

import com.example.submission_made.ui.activity.MainActivity
import com.example.submission_made.ui.activity.MovieDetailsActivity
import com.example.submission_made.ui.activity.MovieFavoritesActivity
import com.example.submission_made.ui.activity.SettingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    internal abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    internal abstract fun movieDetailActivity(): MovieDetailsActivity

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    internal abstract fun movieFavoritesActivity(): MovieFavoritesActivity

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    internal abstract fun reminderSettingsActivity(): SettingActivity

}