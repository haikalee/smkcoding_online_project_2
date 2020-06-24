package com.haikal.project2.data.mathdro.indonesia


import com.google.gson.annotations.SerializedName

data class Confirmed(
    @SerializedName("detail")
    val detail: String,
    @SerializedName("value")
    val value: Int
)