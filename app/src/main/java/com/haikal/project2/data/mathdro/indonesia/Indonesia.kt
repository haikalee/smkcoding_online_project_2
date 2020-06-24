package com.haikal.project2.data.mathdro.indonesia


import com.google.gson.annotations.SerializedName

data class Indonesia(
    @SerializedName("confirmed")
    val confirmed: Confirmed,
    @SerializedName("deaths")
    val deaths: Deaths,
    @SerializedName("lastUpdate")
    val lastUpdate: String,
    @SerializedName("recovered")
    val recovered: Recovered
)