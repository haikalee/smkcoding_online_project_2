package com.haikal.project2


import com.google.gson.annotations.SerializedName

data class GlobalDataItem(
    @SerializedName("attributes")
    val attributes: Attributes
)