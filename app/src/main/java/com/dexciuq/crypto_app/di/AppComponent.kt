package com.dexciuq.crypto_app.di

import android.app.Application
import com.dexciuq.crypto_app.CryptoApplication
import com.dexciuq.crypto_app.di.viewmodel.ViewModelModule
import com.dexciuq.crypto_app.di.worker.WorkerModule
import com.dexciuq.crypto_app.presentation.CoinDetailFragment
import com.dexciuq.crypto_app.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component


@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class, WorkerModule::class])
interface AppComponent {
    fun inject(activity: CoinPriceListActivity)
    fun inject(fragment: CoinDetailFragment)
    fun inject(application: CryptoApplication)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
        ): AppComponent
    }
}