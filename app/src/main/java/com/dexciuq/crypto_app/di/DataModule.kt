package com.dexciuq.crypto_app.di

import android.app.Application
import com.dexciuq.crypto_app.data.data_source.local.AppDatabase
import com.dexciuq.crypto_app.data.data_source.local.CoinInfoDao
import com.dexciuq.crypto_app.data.repository.CoinRepositoryImpl
import com.dexciuq.crypto_app.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {
        @Provides
        fun provideAppDatabase(application: Application): AppDatabase {
            return AppDatabase.getInstance(application)
        }

        @Provides
        fun provideCoinInfoDao(appDatabase: AppDatabase): CoinInfoDao {
            return appDatabase.coinInfoDao()
        }
    }
}