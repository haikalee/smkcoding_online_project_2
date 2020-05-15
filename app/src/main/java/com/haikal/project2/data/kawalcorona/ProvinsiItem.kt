package com.haikal.project2.data.kawalcorona


import com.google.gson.annotations.SerializedName

data class ProvinsiItem(
    @SerializedName("meninggal")
    val meninggal: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("positif")
    val positif: String,
    @SerializedName("sembuh")
    val sembuh: String
)