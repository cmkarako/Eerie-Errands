package com.example.eerieerrands

import com.example.eerieerrands.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface ErrandsApi {
    
    @GET("users/{userId}/children")
    suspend fun listRepos(@Header("Authorization") token: String, @Path("userId") user: String?): Response<List<ChildEntity?>>

    @POST("users/{userId}/children")
    suspend fun createChild(@Header("Authorization") token: String, @Path("userId") user: String?, @Body() child: ChildEntity): Response<List<ChildEntity?>>

    @POST("/register")
    suspend fun register(@Body user: NewUser
    ) : Call<UserEntity>

    @POST("/login")
    fun login(@Body user: LoginUser
    ) : Call<UserResponse>
}

class Client {
    var service: ErrandsApi
    constructor() {
        service = retrofit.create(ErrandsApi::class.java)
    }
    var retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}