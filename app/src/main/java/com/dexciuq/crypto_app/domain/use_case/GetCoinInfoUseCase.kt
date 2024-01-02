package com.dexciuq.crypto_app.domain.use_case

import com.dexciuq.crypto_app.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(fromSymbol: String) = coinRepository.getCoinInfo(fromSymbol)
}