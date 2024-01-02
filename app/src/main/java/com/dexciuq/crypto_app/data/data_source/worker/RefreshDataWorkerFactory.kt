package com.dexciuq.crypto_app.data.data_source.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.dexciuq.crypto_app.data.data_source.local.CoinInfoDao
import com.dexciuq.crypto_app.data.data_source.remote.ApiService
import com.dexciuq.crypto_app.data.mapper.CoinMapper

class RefreshDataWorkerFactory(
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val coinMapper: CoinMapper,
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return RefreshDataWorker(
            appContext,
            workerParameters,
            coinInfoDao,
            apiService,
            coinMapper
        )
    }
}