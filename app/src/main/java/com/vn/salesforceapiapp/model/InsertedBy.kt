package com.vn.salesforceapiapp.model

import com.google.gson.annotations.SerializedName

data class InsertedBy(
    @SerializedName("SmallPhotoUrl")
    val mediumPhotoUrl: String,
    @SerializedName("CompanyName")
    val companyName: String,
)
