package com.example.deliveryfoodapp.backend_services.user_api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserApiService {

    @GET("user/details/{id}/")
    suspend fun getUserById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Map<String, Any?>
}