package com.haikal.project2.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    companion object {
        val url = "https://api.kawalcorona.com/"
        val services: CoronaServices
            get() {
                val r = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
                val t = r.create(CoronaServices::class.java)
                return t
            }
    }
}