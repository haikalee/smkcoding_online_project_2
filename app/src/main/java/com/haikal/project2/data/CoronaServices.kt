package com.haikal.project2.data

import com.haikal.project2.data.kawalcorona.ProvinsiItem
import com.haikal.project2.data.mathdro.indonesia.Indonesia
import com.haikal.project2.data.mathdro.global.GlobalDataItem
import com.haikal.project2.data.mathdro.global.GlobalDetail
import retrofit2.Call
import retrofit2.http.GET

interface CoronaServices {
    @GET("confirmed")
    fun getMathdroDataGlobal(): Call<List<GlobalDataItem>>

    @GET("https://covid19.mathdro.id/api/")
    fun getMathdroGlobal(): Call<GlobalDetail>

    @GET("countries/indonesia")
    fun getMathdroIndonesia(): Call<Indonesia>

    @GET("indonesia/provinsi")
    fun getKawalCoronaProvinsi(): Call<List<ProvinsiItem>>
}