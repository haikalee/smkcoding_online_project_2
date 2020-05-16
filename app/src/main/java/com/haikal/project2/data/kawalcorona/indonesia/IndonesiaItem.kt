package com.haikal.project2.data.kawalcorona.indonesia


import com.google.gson.annotations.SerializedName

data class IndonesiaItem(
    @SerializedName("meninggal")
    val meninggal: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("positif")
    val positif: String,
    @SerializedName("sembuh")
    val sembuh: String
)