package com.example.deliveryfoodapp.backend_services.restaurant_api

import com.example.deliveryfoodapp.backend_services.ApiClient
import com.example.deliveryfoodapp.local_storage_services.Pref
import com.example.deliveryfoodapp.models.Restaurant

object RestaurantEndpoints {

    private val apiService = ApiClient.retrofit.create(RestaurantApiService::class.java)
    private val token = Pref.getUserToken() ?: ""


    suspend fun fetchAllRestaurants(): MutableList<Restaurant> {
        val allRestaurantMap = apiService.getAllRestaurants(
            token = token
        )
        return allRestaurantMap.map { Restaurant.fromMap(it) }.toMutableList()
    }
}