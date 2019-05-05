package com.example.monedero

import android.content.AbstractThreadedSyncAdapter
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.monedero.adapter.CoinAdapter
import com.example.monedero.models.Coin
import com.example.monedero.utils.CoinSerializer
import com.example.monedero.utils.NetworkUtilities
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var viewAdapter: CoinAdapter
    lateinit var viewManager:LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = CoinAdapter(listOf<Coin>()){
            Snackbar.make(rv_main,"click en "+ it.name,Snackbar.LENGTH_SHORT).show()
        }

        CoinsFetch().execute()

    }

    //unit es como un tipo de void en el asynctask
    //in background, in progrss,lo que retorna
    inner class CoinsFetch : AsyncTask<Unit,Unit,List<Coin>>() {
        override fun doInBackground(vararg params: Unit?): List<Coin> {
            val url = NetworkUtilities.buildURL()
            val resultString = NetworkUtilities.getHTTPResult(url)

            val resultJSON = JSONObject(resultString)

            return if (resultJSON.getBoolean("success")){
                CoinSerializer.parseCoins(

                        resultJSON.getJSONArray("docs").toString()
                )
            }
            else{
                listOf<Coin>()
            }
        }

        override fun onPostExecute(result: List<Coin>) {
            if(result.isNotEmpty()){
                viewAdapter.setData(result)
            } else{
                Snackbar.make(rv_main,"no ", Snackbar.LENGTH_SHORT).show()
            }
        }
    }


}
