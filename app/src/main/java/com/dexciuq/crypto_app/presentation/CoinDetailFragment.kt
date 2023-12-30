package com.dexciuq.crypto_app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dexciuq.crypto_app.databinding.FragmentCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailFragment : Fragment() {

    private val binding by lazy { FragmentCoinDetailBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[CoinViewModel::class.java] }
    private lateinit var fromSymbol: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun parseArgs() {
        fromSymbol = requireArguments().getString(KEY_FROM_SYMBOL, EMPTY_FROM_SYMBOL)
    }

    private fun setupObservers() {
        viewModel.getDetailInfo(fromSymbol).observe(viewLifecycleOwner) {
            with(binding) {
                tvPrice.text = it.price
                tvMinPrice.text = it.lowDay
                tvMaxPrice.text = it.highDay
                tvLastMarket.text = it.lastMarket
                tvLastUpdate.text = it.lastUpdate
                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.toSymbol
                Picasso.get().load(it.imageUrl).into(ivLogoCoin)
            }
        }
    }

    companion object {
        private const val KEY_FROM_SYMBOL = "from_symbol"
        private const val EMPTY_FROM_SYMBOL = ""

        fun newInstance(fromSymbol: String): CoinDetailFragment {
            return CoinDetailFragment().apply {
                arguments = bundleOf(KEY_FROM_SYMBOL to fromSymbol)
            }
        }
    }
}
