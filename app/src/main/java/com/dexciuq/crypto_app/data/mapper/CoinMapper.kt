package com.dexciuq.crypto_app.data.mapper

import com.dexciuq.crypto_app.data.model.local.CoinInfoEntity
import com.dexciuq.crypto_app.data.model.remote.CoinInfoDto
import com.dexciuq.crypto_app.data.model.remote.CoinInfoJsonContainerDto
import com.dexciuq.crypto_app.data.model.remote.CoinNameListDto
import com.dexciuq.crypto_app.domain.model.CoinInfo
import com.google.gson.Gson
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class CoinMapper @Inject constructor() {
    fun fromDtoToEntity(dto: CoinInfoDto): CoinInfoEntity = CoinInfoEntity(
        fromSymbol = dto.fromSymbol.orEmpty(),
        toSymbol = dto.toSymbol.orEmpty(),
        price = dto.price.orEmpty(),
        lastUpdate = dto.lastUpdate ?: 0,
        highDay = dto.highDay.orEmpty(),
        lowDay = dto.lowDay.orEmpty(),
        lastMarket = dto.lastMarket.orEmpty(),
        imageUrl = BASE_IMAGE_URL + dto.imageUrl.orEmpty(),
    )

    fun fromEntityToDomain(entity: CoinInfoEntity): CoinInfo = CoinInfo(
        fromSymbol = entity.fromSymbol,
        toSymbol = entity.toSymbol,
        price = entity.price,
        lastUpdate = convertTimestampToTime(entity.lastUpdate),
        highDay = entity.highDay,
        lowDay = entity.lowDay,
        lastMarket = entity.lastMarket,
        imageUrl = entity.imageUrl,
    )

    fun fromJsonContainerToListCoinInfo(
        coinInfoJsonContainerDto: CoinInfoJsonContainerDto
    ): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = coinInfoJsonContainerDto.jsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun fromNameListToString(nameListDto: CoinNameListDto): String {
        return nameListDto.names?.map {
            it.coinNameDto?.name
        }?.joinToString(",").orEmpty()
    }

    private fun convertTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    companion object {
        const val BASE_IMAGE_URL = "https://cryptocompare.com"
    }
}