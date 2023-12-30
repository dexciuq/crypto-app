package com.dexciuq.crypto_app.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dexciuq.crypto_app.databinding.ActivityCoinPrceListBinding
import com.dexciuq.crypto_app.presentation.adapters.CoinInfoAdapter

class CoinPriceListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCoinPrceListBinding.inflate(layoutInflater) }
    private val adapter by lazy { CoinInfoAdapter() }
    private val viewModel by lazy { ViewModelProvider(this)[CoinViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter.onCoinClickListener = {
            val intent = CoinDetailActivity.newIntent(this, it.fromSymbol)
            startActivity(intent)
        }
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null
    }

    private fun setupObservers() {
        viewModel.coinInfoList.observe(this, adapter::submitList)
    }
}
