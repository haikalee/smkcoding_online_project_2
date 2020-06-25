package com.haikal.project2.data.api

import com.haikal.project2.data.kawalcorona.indonesia.IndonesiaItem
import com.haikal.project2.data.kawalcorona.provinsi.ProvinsiItem
import com.haikal.project2.data.mathdro.global.GlobalDataItem
import com.haikal.project2.data.mathdro.global.GlobalDetail
import retrofit2.Call
import retrofit2.http.GET

interface CoronaServices {
    @GET("confirmed")
    fun getMathdroDataGlobal(): Call<List<GlobalDataItem>>

    @GET("https://covid19.mathdro.id/api/")
    fun getMathdroGlobal(): Call<GlobalDetail>

    @GET("indonesia")
    fun getKawalCoronaIndonesia(): Call<List<IndonesiaItem>>

    @GET("indonesia/provinsi")
    fun getKawalCoronaProvinsi(): Call<List<ProvinsiItem>>
}