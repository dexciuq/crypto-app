package com.dexciuq.crypto_app.domain.use_case

import com.dexciuq.crypto_app.domain.repository.CoinRepository

class LoadDataUseCase(
    private val coinRepository: CoinRepository
) {
    operator fun invoke() = coinRepository.loadData()
}