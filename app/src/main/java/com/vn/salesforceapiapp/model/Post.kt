package com.vn.salesforceapiapp.model

import com.google.gson.annotations.SerializedName

data class Post (
    @SerializedName("Id")
    val Id: String,
    @SerializedName("ParentId")
    val parentId: String,
    @SerializedName("Body")
    val body: String,
    @SerializedName("CreatedDate")
    val createdDate: String,
    @SerializedName("CreatedBy")
    val createdBy: User,
    @SerializedName("InsertedBy")
    val insertedBy: InsertedBy,
)