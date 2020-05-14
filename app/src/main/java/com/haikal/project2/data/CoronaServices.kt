package com.haikal.project2.data

import com.haikal.project2.data.global.Attributes
import com.haikal.project2.data.global.GlobalDataItem
import com.haikal.project2.data.lokal.indonesia.IndonesiaDataItem
import com.haikal.project2.data.lokal.provinsi.ProvinsiDataItem
import retrofit2.Call
import retrofit2.http.GET

interface CoronaServices {
    @GET("https://api.kawalcorona.com/")
    fun getDataGlobal(): Call<List<GlobalDataItem>>

    @GET("indonesia")
    fun getDataIndonesia(): Call<List<IndonesiaDataItem>>

    @GET("indonesia/provinsi")
    fun getDataProvinsi(): Call<List<ProvinsiDataItem>>
}