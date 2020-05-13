package com.haikal.project2

import retrofit2.Call
import retrofit2.http.GET

interface CoronaServices {
    @GET("https://api.kawalcorona.com/")
    fun getData(): Call<List<GlobalDataItem>>
}