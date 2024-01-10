package com.example.tveek

import android.app.Application
import com.example.tveek.di.applicationModule
import com.example.tveek.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

class TVeekApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
        startKoin {
            androidContext(this@TVeekApplication)
            modules(listOf(networkModule, applicationModule))
        }
    }
}