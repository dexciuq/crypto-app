package com.dexciuq.crypto_app.data.datasource.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dexciuq.crypto_app.data.model.local.CoinInfoEntity

@Dao
interface CoinInfoDao {
    @Query("SELECT * FROM full_price_list ORDER BY lastUpdate DESC")
    fun getCoinInfoEntityList(): LiveData<List<CoinInfoEntity>>

    @Query("SELECT * FROM full_price_list WHERE fromSymbol == :fSym LIMIT 1")
    fun getCoinInfoEntity(fSym: String): LiveData<CoinInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinInfoEntityList(coinInfoEntityList: List<CoinInfoEntity>)
}
