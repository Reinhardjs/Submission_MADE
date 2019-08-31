package com.example.submission_made.di.builder

import com.example.submission_made.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    internal abstract fun mainActivity(): MainActivity

}