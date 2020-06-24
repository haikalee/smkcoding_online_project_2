package com.haikal.project2.data.mathdro.indonesia


import com.google.gson.annotations.SerializedName

data class Recovered(
    @SerializedName("detail")
    val detail: String,
    @SerializedName("value")
    val value: Int
)