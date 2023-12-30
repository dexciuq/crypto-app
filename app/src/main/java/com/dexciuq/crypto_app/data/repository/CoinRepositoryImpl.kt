package com.dexciuq.crypto_app.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dexciuq.crypto_app.data.mapper.CoinMapper
import com.dexciuq.crypto_app.data.data_source.local.AppDatabase
import com.dexciuq.crypto_app.data.data_source.local.CoinInfoDao
import com.dexciuq.crypto_app.data.data_source.remote.ApiFactory
import com.dexciuq.crypto_app.data.data_source.remote.ApiService
import com.dexciuq.crypto_app.domain.model.CoinInfo
import com.dexciuq.crypto_app.domain.repository.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    application: Application,
) : CoinRepository {

    private val coinInfoDao: CoinInfoDao = AppDatabase.getInstance(application).coinInfoDao()
    private val apiService: ApiService = ApiFactory.apiService
    private val coinMapper: CoinMapper = CoinMapper()

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> =
        coinInfoDao.getCoinInfoEntityList().map {
            it.map(coinMapper::fromEntityToDomain)
        }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> =
        coinInfoDao.getCoinInfoEntity(fromSymbol).map(coinMapper::fromEntityToDomain)

    override suspend fun loadData() {
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
}