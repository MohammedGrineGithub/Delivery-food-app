package com.example.deliveryfoodapp.backend_services.restaurant_api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface RestaurantApiService {

    /** ******************************** Get all restaurants ******************************** **/
    @GET("restaurant/all/")
    suspend fun getAllRestaurants(
        @Header("Authorization") token: String,
    ): MutableList<Map<String, Any?>>

    /** **************************** Get all restaurant comments **************************** **/
    @GET("restaurant/comments/{id}/")
    suspend fun getCommentsByRestaurantID(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ) : MutableList<Map<String, Any?>>

    /** ******************************* Get restaurant menu ********************************* **/
    @GET("restaurant/menu/{id}/")
    suspend fun getRestaurantMenuByMenuID(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ) : MutableList<Map<String, Any?>>
}