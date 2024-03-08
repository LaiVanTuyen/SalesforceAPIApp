package com.vn.salesforceapiapp.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Name")
    val name: String,
)
