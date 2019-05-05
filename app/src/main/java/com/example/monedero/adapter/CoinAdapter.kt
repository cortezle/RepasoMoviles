package com.example.monedero.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.monedero.R
import com.example.monedero.models.Coin
import kotlinx.android.synthetic.main.item_list_coin.view.*
import java.util.*

class CoinAdapter(var items: List<Coin>,
                  var listener:(Coin)->Unit ):
    RecyclerView.Adapter<CoinAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_coin,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(items: List<Coin>){
        this.items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position],listener)
    }

    class ViewHolder (var item : View): RecyclerView.ViewHolder(item){
        fun bind(coin: Coin,listener: (Coin) -> Unit){
            with(item){
                tv_name.text = coin.name
                tv_value.text = coin.value.toString()
                setOnClickListener { listener(coin) }
            }
        }
    }
}