package com.dexciuq.crypto_app.di

import android.app.Application
import com.dexciuq.crypto_app.data.datasource.local.AppDatabase
import com.dexciuq.crypto_app.data.datasource.local.CoinInfoDao
import com.dexciuq.crypto_app.data.datasource.remote.ApiFactory
import com.dexciuq.crypto_app.data.datasource.remote.ApiService
import com.dexciuq.crypto_app.data.repository.CoinRepositoryImpl
import com.dexciuq.crypto_app.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {
        @Provides
        @ApplicationScope
        fun provideAppDatabase(application: Application): AppDatabase {
            return AppDatabase.getInstance(application)
        }

        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(appDatabase: AppDatabase): CoinInfoDao {
            return appDatabase.coinInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}