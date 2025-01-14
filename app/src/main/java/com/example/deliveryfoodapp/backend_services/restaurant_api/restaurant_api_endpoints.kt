package com.example.deliveryfoodapp.backend_services.restaurant_api

import com.example.deliveryfoodapp.backend_services.ApiClient
import com.example.deliveryfoodapp.local_storage_services.Pref
import com.example.deliveryfoodapp.models.Category
import com.example.deliveryfoodapp.models.Comment
import com.example.deliveryfoodapp.models.Restaurant
import com.example.deliveryfoodapp.models.RestaurantMenu
import com.example.deliveryfoodapp.models.SocialMediaLink

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
        val commentsList = apiService.getCommentsByRestaurantID(
            id = restaurantID,
            token = token
        )
        return commentsList.map { Comment.fromMap(it) }.toMutableList()
    }

    /** ******************************* Get restaurant menu ********************************* **/
    suspend fun fetchRestaurantMenuByMenuID(menuID: Int): RestaurantMenu {
        val categories = apiService.getRestaurantMenuByMenuID(
            id = menuID,
            token = token
        )
        return RestaurantMenu(
            id = menuID,
            categories = categories.map { Category.fromMap(it) }.toMutableList()
        )
    }

    /** ************************ Get all restaurant social media links ********************** **/
    suspend fun fetchSocialMediaLinksByRestaurantID(restaurantID: Int): MutableList<SocialMediaLink> {
        val socialMediaLinkList = apiService.getSocialMediaLinksByRestaurantID(
            id = restaurantID,
            token = token
        )
        return socialMediaLinkList.map { SocialMediaLink.fromMap(it) }.toMutableList()
    }

    /** ************************ search restaurant by name ********************** **/
    suspend fun searchRestaurantByName(name: String): MutableList<Restaurant> {
        val restaurantList = apiService.searchRestaurantByName(
            token = token,
            body = mapOf("restaurant_name" to name)
        )
        return restaurantList.map { Restaurant.fromMap(it) }.toMutableList()
    }

    /** ******************** Filter restaurant by wilaya or cuisine type ****************** **/
    suspend fun filterRestaurant(wilayaID: Int?, cuisineID: Int?): MutableList<Restaurant> {
        val body = mutableMapOf<String, Int>()
        if (wilayaID != null) {
            body["wilaya"] = wilayaID
        }
        if (cuisineID != null){
            body["cuisine"] = cuisineID
        }
        val restaurantList = apiService.filterRestaurant(
            token = token,
            body = body
        )
        return restaurantList.map { Restaurant.fromMap(it) }.toMutableList()
    }
}