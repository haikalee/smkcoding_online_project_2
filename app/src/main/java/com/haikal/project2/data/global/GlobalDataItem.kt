package com.haikal.project2.data.global


import com.google.gson.annotations.SerializedName

data class GlobalDataItem(
    @SerializedName("attributes")
    val attributes: Attributes
)