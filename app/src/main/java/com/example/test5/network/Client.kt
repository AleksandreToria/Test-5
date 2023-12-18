package com.example.test5.network

import com.example.test5.service.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Client {
    private const val BASE_URL = "https://run.mocky.io/v3/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}

object ApiClient {
    val apiService: ApiService by lazy {
        Client.retrofit.create(ApiService::class.java)
    }
}