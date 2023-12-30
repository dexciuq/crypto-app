package com.dexciuq.crypto_app.domain.repository

import androidx.lifecycle.LiveData
import com.dexciuq.crypto_app.domain.model.CoinInfo

interface CoinRepository {
    fun getCoinInfoList(): LiveData<List<CoinInfo>>
    fun getCoinInfo(): LiveData<CoinInfo>
}