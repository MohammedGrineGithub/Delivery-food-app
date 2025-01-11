package com.example.deliveryfoodapp.backend_services.restaurant_api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface RestaurantApiService {

    @GET("restaurant/all/")
    suspend fun getAllRestaurants(
        @Header("Authorization") token: String,
    ): MutableList<Map<String, Any?>>

    @GET("restaurant/comments/{id}/")
    suspend fun getCommentsByRestaurantID(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ) : MutableList<Map<String, Any?>>
}