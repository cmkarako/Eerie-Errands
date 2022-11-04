package com.example.eerieerrands

import android.widget.Toast
import com.example.eerieerrands.model.ChildEntity
import com.example.eerieerrands.model.UserEntity
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("children")
    suspend fun getAllChildren(@Query("userId") userId: String): List<ChildEntity>

    @POST("children")
    suspend fun createNewChild(@Query("userId") userId: String, child: ChildEntity)

    @FormUrlEncoded
    @POST("/register")
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("firstName") firstName : String,
        @Field("lastName") lastName : String
    ) : Call<UserEntity>

}