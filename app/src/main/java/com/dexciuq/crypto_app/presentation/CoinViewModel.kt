package com.dexciuq.crypto_app.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.dexciuq.crypto_app.data.repository.CoinRepositoryImpl
import com.dexciuq.crypto_app.domain.repository.CoinRepository
import com.dexciuq.crypto_app.domain.use_case.GetCoinInfoListUseCase
import com.dexciuq.crypto_app.domain.use_case.GetCoinInfoUseCase
import com.dexciuq.crypto_app.domain.use_case.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val coinRepository: CoinRepository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(coinRepository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(coinRepository)
    private val loadDataUseCase = LoadDataUseCase(coinRepository)

    val coinInfoList = getCoinInfoListUseCase()

    init {
        loadDataUseCase()
    }

    fun getDetailInfo(fromSymbol: String) = getCoinInfoUseCase(fromSymbol)
}