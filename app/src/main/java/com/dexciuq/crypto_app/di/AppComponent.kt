package com.dexciuq.crypto_app.di

import android.app.Application
import com.dexciuq.crypto_app.presentation.CoinDetailFragment
import com.dexciuq.crypto_app.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class, PresentationModule::class])
interface AppComponent {
    fun inject(activity: CoinPriceListActivity)
    fun inject(fragment: CoinDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
        ): AppComponent
    }
}