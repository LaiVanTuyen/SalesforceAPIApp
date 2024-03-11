package com.vn.salesforceapiapp.api

import com.vn.salesforceapiapp.model.Feed
import com.vn.salesforceapiapp.model.Post
import com.vn.salesforceapiapp.model.Token
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface APIService {
    data class PostRequest(
        val title: String
    )
    @GET("/services/apexrest/api/Feed/0F92t000000XhKnCAK")
    fun getPosts(
        @Header("Authorization") token: String
    ): retrofit2.Call<List<Feed>>

    @POST("/services/apexrest/api/Feed")
    fun createPost(
        @Header("Authorization") token: String,
        @Body postRequest: PostRequest
    ): retrofit2.Call<String>

    @Headers("Content-type: application/json")
    @POST("/services/oauth2/token")
    fun getToken(
       @Query("grant_type") grant_type: String,
       @Query("client_id") client_id: String,
       @Query("client_secret") client_secret: String,
       @Query("username") username: String,
       @Query("password") password: String
    ): retrofit2.Call<Token>
}