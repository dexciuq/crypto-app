package com.dexciuq.crypto_app.data.model.remote

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoJsonContainerDto(
    @SerializedName("RAW")
    @Expose
    val jsonObject: JsonObject? = null
)
