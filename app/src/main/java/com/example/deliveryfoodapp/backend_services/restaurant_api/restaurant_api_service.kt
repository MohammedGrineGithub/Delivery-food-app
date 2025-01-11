package com.example.deliveryfoodapp.backend_services.restaurant_api

import retrofit2.http.GET
import retrofit2.http.Header

interface RestaurantApiService {

    @GET("restaurant/all/")
    suspend fun getAllRestaurants(
        @Header("Authorization") token: String,
    ): MutableList<Map<String, Any?>>
}