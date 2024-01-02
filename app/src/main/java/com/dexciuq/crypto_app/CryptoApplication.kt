package com.dexciuq.crypto_app

import android.app.Application
import com.dexciuq.crypto_app.di.DaggerAppComponent

class CryptoApplication : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}