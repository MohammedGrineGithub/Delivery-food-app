package com.example.deliveryfoodapp.backend_services.user_api

import com.example.deliveryfoodapp.backend_services.ApiClient
import com.example.deliveryfoodapp.local_storage_services.Pref
import com.example.deliveryfoodapp.models.Comment
import com.example.deliveryfoodapp.models.User

object UserEndpoints {

    private val apiService = ApiClient.retrofit.create(UserApiService::class.java)
    private val token = Pref.getUserToken() ?: ""


    suspend fun fetchUserById(id: Int): User {
        val userMap = apiService.getUserById(
            id = id,
            token = token
        )
        return User.fromMap(userMap)
    }

    suspend fun fetchCommentsByRestaurantID(restaurantID: Int): MutableList<Comment> {
        val commentsList = apiService.getCommentsByRestaurantID(
            id = restaurantID,
            token = token
        )
        // Convert each map into a Comment object using Comment.fromMap
        return commentsList.map { Comment.fromMap(it) }.toMutableList()
    }
}