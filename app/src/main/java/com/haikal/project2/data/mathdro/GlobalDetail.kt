package com.haikal.project2.data.mathdro


import com.google.gson.annotations.SerializedName

data class GlobalDetail(
    @SerializedName("confirmed")
    val confirmed: Confirmed,
    @SerializedName("deaths")
    val deaths: Deaths,
    @SerializedName("recovered")
    val recovered: Recovered
)