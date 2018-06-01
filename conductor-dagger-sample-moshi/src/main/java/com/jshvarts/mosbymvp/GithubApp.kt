package com.jshvarts.mosbymvp

import android.app.Application
import com.jshvarts.mosbymvp.di.AppComponent
import com.jshvarts.mosbymvp.di.DaggerAppComponent
import timber.log.Timber

class GithubApp : Application() {
    companion object {
        lateinit var component: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
                .application(this)
                .build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}