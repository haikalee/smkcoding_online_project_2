package com.haikal.project2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    companion object {
        final val url = "https://api.kawalcorona.com/"
        final val services: CoronaServices
            get() {
                val r = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
                val t = r.create(CoronaServices::class.java)
                return t
            }
    }
}