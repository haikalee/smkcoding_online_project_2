package com.haikal.project2.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    companion object {
        val mathdro = "https://covid19.mathdro.id/api/"
        val kawalCorona = "https://api.kawalcorona.com/"
        val servicesMathdro: CoronaServices
            get() {
                val r1 = Retrofit.Builder().baseUrl(mathdro).addConverterFactory(GsonConverterFactory.create()).build()
                val t1 =  r1.create(CoronaServices::class.java)
                return t1
            }
        val servicesKawalCorona: CoronaServices
            get() {
                val r2 = Retrofit.Builder().baseUrl(kawalCorona).addConverterFactory(GsonConverterFactory.create()).build()
                val t2 =  r2.create(CoronaServices::class.java)
                return t2
            }
    }
}