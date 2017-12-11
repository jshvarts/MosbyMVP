package com.jshvarts.mosbymvp

import android.app.Application
import timber.log.Timber

class GreetingApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}