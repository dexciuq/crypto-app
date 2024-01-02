package com.dexciuq.crypto_app.presentation

import androidx.lifecycle.ViewModel
import com.dexciuq.crypto_app.domain.use_case.GetCoinInfoListUseCase
import com.dexciuq.crypto_app.domain.use_case.GetCoinInfoUseCase
import com.dexciuq.crypto_app.domain.use_case.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase,
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    init {
        loadDataUseCase()
    }

    fun getDetailInfo(fromSymbol: String) = getCoinInfoUseCase(fromSymbol)
}