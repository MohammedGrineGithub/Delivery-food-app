package com.example.deliveryfoodapp.backend_services.user_api

import com.example.deliveryfoodapp.backend_services.ApiClient
import com.example.deliveryfoodapp.local_storage_services.Pref
import com.example.deliveryfoodapp.models.User

object UserEndpoints {

    private val apiService = ApiClient.retrofit.create(ApiService::class.java)
    private val token = Pref.getUserToken() ?: ""


    suspend fun fetchUserById(id: Int): User {
        val userMap = apiService.getUserById(
            id = id,
            token = token
        )
        return User.fromMap(userMap)
    }
}