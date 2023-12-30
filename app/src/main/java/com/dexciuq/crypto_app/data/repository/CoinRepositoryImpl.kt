package com.dexciuq.crypto_app.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.dexciuq.crypto_app.data.data_source.local.AppDatabase
import com.dexciuq.crypto_app.data.data_source.local.CoinInfoDao
import com.dexciuq.crypto_app.data.data_source.worker.RefreshDataWorker
import com.dexciuq.crypto_app.data.mapper.CoinMapper
import com.dexciuq.crypto_app.domain.model.CoinInfo
import com.dexciuq.crypto_app.domain.repository.CoinRepository

class CoinRepositoryImpl(
    private val application: Application,
) : CoinRepository {

    private val coinInfoDao: CoinInfoDao = AppDatabase.getInstance(application).coinInfoDao()
    private val coinMapper: CoinMapper = CoinMapper()

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> =
        coinInfoDao.getCoinInfoEntityList().map {
            it.map(coinMapper::fromEntityToDomain)
        }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> =
        coinInfoDao.getCoinInfoEntity(fromSymbol).map(coinMapper::fromEntityToDomain)

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}