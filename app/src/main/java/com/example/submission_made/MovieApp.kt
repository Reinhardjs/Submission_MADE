package com.example.submission_made

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.example.submission_made.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MovieApp : Application(), HasActivityInjector, LifecycleOwner {
    override fun getLifecycle(): Lifecycle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Inject
    lateinit var activityDispatchingInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initializeComponent()
        setInstance(this)
    }

    fun initializeComponent() {
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityDispatchingInjector
    }

    companion object {

        var appContext: MovieApp? = null
            set

        @Synchronized
        fun setInstance(app: MovieApp) {
            appContext = app
        }

        fun getContext(): Context{
            return appContext?.applicationContext!!
        }
    }
}