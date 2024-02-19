package com.sawitpro.weightbridge

import android.app.Application
import com.sawitpro.weightbridge.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.java.KoinAndroidApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        val koinApp = KoinAndroidApplication.create(this, Level.NONE)
            .modules(Modules.getAppComponents())
            .androidContext(this)

        startKoin(koinApp)
    }
}