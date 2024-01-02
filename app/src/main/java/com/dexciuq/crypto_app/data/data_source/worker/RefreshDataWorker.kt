package com.dexciuq.crypto_app.data.data_source.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.dexciuq.crypto_app.data.data_source.local.CoinInfoDao
import com.dexciuq.crypto_app.data.data_source.remote.ApiService
import com.dexciuq.crypto_app.data.mapper.CoinMapper
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val coinMapper: CoinMapper,
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fromSymbols = topCoins.run(coinMapper::fromNameListToString)
                val jsonContainer = apiService.getFullPriceList(fSyms = fromSymbols)
                val coinInfoDtoList = jsonContainer.run(coinMapper::fromJsonContainerToListCoinInfo)
                val coinInfoEntityList = coinInfoDtoList.map(coinMapper::fromDtoToEntity)
                coinInfoDao.insertCoinInfoEntityList(coinInfoEntityList)
            } catch (e: Exception) {
                TODO("Not yet implemented")
            }
            delay(10000)
        }
    }

    companion object {
        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>()
                .build()
        }
    }
}