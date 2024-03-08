package com.vn.salesforceapiapp.model

import com.google.gson.annotations.SerializedName

data class Token (
    @SerializedName("access_token")
    val access_token: String,
    @SerializedName("instance_url")
    val instance_url: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("token_type")
    val token_type: String,
    @SerializedName("issued_at")
    val issued_at: String,
    @SerializedName("signature")
    val signature: String
)