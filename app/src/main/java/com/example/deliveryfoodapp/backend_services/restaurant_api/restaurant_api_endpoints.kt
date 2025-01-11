package com.example.deliveryfoodapp.backend_services.restaurant_api

import com.example.deliveryfoodapp.backend_services.ApiClient
import com.example.deliveryfoodapp.local_storage_services.Pref
import com.example.deliveryfoodapp.models.Category
import com.example.deliveryfoodapp.models.Comment
import com.example.deliveryfoodapp.models.Restaurant
import com.example.deliveryfoodapp.models.RestaurantMenu

object RestaurantEndpoints {

    private val apiService = ApiClient.retrofit.create(RestaurantApiService::class.java)
    private val token = Pref.getUserToken() ?: ""

    /** ******************************** Get all restaurants ******************************** **/
    suspend fun fetchAllRestaurants(): MutableList<Restaurant> {
        val allRestaurantMap = apiService.getAllRestaurants(
            token = token
        )
        return allRestaurantMap.map { Restaurant.fromMap(it) }.toMutableList()
    }

    /** **************************** Get all restaurant comments **************************** **/
    suspend fun fetchCommentsByRestaurantID(restaurantID: Int): MutableList<Comment> {
        val commentsList = RestaurantEndpoints.apiService.getCommentsByRestaurantID(
            id = restaurantID,
            token = token
        )
        return commentsList.map { Comment.fromMap(it) }.toMutableList()
    }

    /** ******************************* Get restaurant menu ********************************* **/
    suspend fun fetchRestaurantMenuByMenuID(menuID: Int): RestaurantMenu {
        val categories = RestaurantEndpoints.apiService.getRestaurantMenuByMenuID(
            id = menuID,
            token = token
        )
        return RestaurantMenu(
            id = menuID,
            categories = categories.map { Category.fromMap(it) }.toMutableList()
        )
    }
}