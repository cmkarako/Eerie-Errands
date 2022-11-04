package com.example.eerieerrands

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitService {
    private lateinit var retrofit : Retrofit


//    fun provideRetrofitInstance(BASE_URL: String) : ApiInterface =
//        Retrofit.Builder()
//        .baseUrl("http://192.168.4.24:8080/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//        .create(ApiInterface::class.java)

    fun RetrofitService() {
        initializeRetrofit()
    }

    fun initializeRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun getRetrofit() : Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }


}