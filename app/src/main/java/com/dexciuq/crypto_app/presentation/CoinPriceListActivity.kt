package com.dexciuq.crypto_app.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dexciuq.crypto_app.databinding.ActivityCoinPrceListBinding
import com.dexciuq.crypto_app.domain.model.CoinInfo
import com.dexciuq.crypto_app.presentation.adapters.CoinInfoAdapter

class CoinPriceListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCoinPrceListBinding.inflate(layoutInflater) }
    private val adapter by lazy { CoinInfoAdapter() }
    private val viewModel by lazy { ViewModelProvider(this)[CoinViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinInfo: CoinInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinInfo.fromSymbol
                )
                startActivity(intent)
            }
        }
        binding.rvCoinPriceList.adapter = adapter

        viewModel.coinInfoList.observe(this) {
            adapter.coinInfoList = it
        }
    }
}
