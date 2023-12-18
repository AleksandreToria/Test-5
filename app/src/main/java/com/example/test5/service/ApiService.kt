package com.example.test5.service

import com.example.test5.dataclass.ApiResponse
import retrofit2.http.GET

interface ApiService {
    @GET("83160a49-fe85-46ba-bcf8-3cf5aa09f92b")
    suspend fun get(): ApiResponse
}