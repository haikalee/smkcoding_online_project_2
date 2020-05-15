package com.haikal.project2.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    companion object {
        val mathdro = "https://covid19.mathdro.id/api/"
        val kawalCorona = "https://api.kawalcorona.com/"
        val servicesMathdro: CoronaServices
            get() {
                val r = Retrofit.Builder().baseUrl(mathdro).addConverterFactory(GsonConverterFactory.create()).build()
                val t =  r.create(CoronaServices::class.java)
                return t
            }
        val servicesKawalCorona: CoronaServices
            get() {
                val r = Retrofit.Builder().baseUrl(kawalCorona).addConverterFactory(GsonConverterFactory.create()).build()
                val t =  r.create(CoronaServices::class.java)
                return t
            }
    }
}