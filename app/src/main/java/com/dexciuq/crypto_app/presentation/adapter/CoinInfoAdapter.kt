package com.dexciuq.crypto_app.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dexciuq.crypto_app.R
import com.dexciuq.crypto_app.databinding.ItemCoinInfoBinding
import com.dexciuq.crypto_app.domain.model.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter :
    ListAdapter<CoinInfo, CoinInfoAdapter.CoinInfoViewHolder>(CoinInfoDiffCallback) {

    var onCoinClickListener: (CoinInfo) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        return CoinInfoViewHolder(
            ItemCoinInfoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CoinInfoViewHolder(
        private val binding: ItemCoinInfoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(coinInfo: CoinInfo) {
            binding.tvSymbols.text = itemView.context.getString(
                R.string.symbols_template,
                coinInfo.fromSymbol,
                coinInfo.toSymbol
            )
            binding.tvLastUpdate.text = itemView.context.getString(
                R.string.last_update_template,
                coinInfo.lastUpdate
            )
            binding.tvPrice.text = coinInfo.price

            Picasso.get().load(coinInfo.imageUrl).into(binding.ivLogoCoin)

            binding.root.setOnClickListener {
                onCoinClickListener(coinInfo)
            }
        }
    }
}