package com.vn.salesforceapiapp.model

import com.google.gson.annotations.SerializedName

data class Feed (
    @SerializedName("feedItem")
    val feed: Post
)