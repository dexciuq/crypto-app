package com.dexciuq.crypto_app.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dexciuq.crypto_app.R
import com.dexciuq.crypto_app.databinding.ItemCoinInfoBinding
import com.dexciuq.crypto_app.domain.model.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList: List<CoinInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        return CoinInfoViewHolder(
            ItemCoinInfoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = coinInfoList.size

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coinInfo = coinInfoList[position]
        holder.bind(coinInfo)
    }

    inner class CoinInfoViewHolder(
        private val binding: ItemCoinInfoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(coinInfo: CoinInfo) {
            binding.tvSymbols.text = itemView.context.resources.getString(
                R.string.symbols_template,
                coinInfo.fromSymbol,
                coinInfo.toSymbol
            )
            binding.tvLastUpdate.text = itemView.context.resources.getString(
                R.string.last_update_template,
                coinInfo.lastUpdate
            )
            binding.tvPrice.text = coinInfo.price

            Picasso.get().load(coinInfo.imageUrl).into(binding.ivLogoCoin)

            binding.root.setOnClickListener {
                onCoinClickListener?.onCoinClick(coinInfo)
            }
        }
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinInfo: CoinInfo)
    }
}