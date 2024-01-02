package com.dexciuq.crypto_app

import android.app.Application
import androidx.work.Configuration
import com.dexciuq.crypto_app.data.worker.WorkManagerFactory
import com.dexciuq.crypto_app.di.DaggerAppComponent
import javax.inject.Inject

class CryptoApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workManagerFactory: WorkManagerFactory

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workManagerFactory)
            .build()

    override fun onCreate() {
        appComponent.inject(this)
        super.onCreate()
    }
}