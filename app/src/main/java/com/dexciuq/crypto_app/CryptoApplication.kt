package com.dexciuq.crypto_app

import android.app.Application
import androidx.work.Configuration
import com.dexciuq.crypto_app.data.data_source.local.AppDatabase
import com.dexciuq.crypto_app.data.data_source.remote.ApiFactory
import com.dexciuq.crypto_app.data.data_source.worker.RefreshDataWorkerFactory
import com.dexciuq.crypto_app.data.mapper.CoinMapper
import com.dexciuq.crypto_app.di.DaggerAppComponent

class CryptoApplication : Application(), Configuration.Provider {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(
                RefreshDataWorkerFactory(
                    coinInfoDao = AppDatabase.getInstance(this).coinInfoDao(),
                    apiService = ApiFactory.apiService,
                    coinMapper = CoinMapper()
                )
            ).build()
}