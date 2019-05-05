package com.example.monedero.utils

import android.net.Uri
import java.net.URL

class NetworkUtilities{


     companion object {
         const val BASE_URL = "https://thawing-stream-49083.herokuapp.com/"
         const val PATH_COIN = "coin"


         fun buildURL(id: String) = URL( Uri.parse(BASE_URL)
                 .buildUpon().appendPath(PATH_COIN).appendPath(id).build().toString())

         fun buildURL() = URL(Uri.parse(BASE_URL)
                 .buildUpon().build().toString())

         fun getHTTPResult (url : URL) = url.readText()
         // si hubiera una key en la api seria
         // fun getHTTPResult (url : URL) = url.openConnection().addRequestProperty("Authorization","Bear {$token}")
     }
}