package com.example.monedero.utils

import com.example.monedero.models.Coin
import org.json.JSONArray
import org.json.JSONObject

class CoinSerializer {

    companion object {

        //devuelve lista de obj
        fun parseCoins (coinsText: String): List<Coin>{
            var coinsJSON = JSONArray(coinsText)
            var coins : MutableList<Coin> = MutableList(coinsJSON.length()-1){
            parseCoin(coinsJSON[it].toString())}
            return coins

        }

        //devuelve obj
        fun parseCoin(coinText: String):Coin {

            val coinJSON = JSONObject(coinText)

            return with(coinJSON){
                Coin(getString("name"),getDouble("value"))
            }
        }
    }
}