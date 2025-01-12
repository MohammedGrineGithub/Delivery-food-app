package com.example.deliveryfoodapp.backend_services.user_api

import com.example.deliveryfoodapp.backend_services.ApiClient
import com.example.deliveryfoodapp.local_storage_services.Pref
import com.example.deliveryfoodapp.models.Notification
import com.example.deliveryfoodapp.models.User

object UserEndpoints {

    private val apiService = ApiClient.retrofit.create(UserApiService::class.java)
    private val token = Pref.getUserToken() ?: ""


    /** ***************************** Get user information ******************************** **/
    suspend fun fetchUserById(id: Int): User {
        val userMap = apiService.getUserById(
            id = id,
            token = token
        )
        return User.fromMap(userMap)
    }

    /** ***************************** Get user notifications ******************************** **/
    suspend fun fetchAllUserNotificationsByUserID(id : Int) : MutableList<Notification> {
        val notifications = apiService.getNotificationsByUserID(
            id = id,
            token = token
        )
        return notifications.map { Notification.fromMap(it) }.toMutableList()
    }

    /** ***************************** Update user has notifications ******************************** **/
    suspend fun updateUserHasNotifications(id: Int): Map<String, Any?> {
        return apiService.updateUserHasNotifications(
            token = token,
            id = id
        )
    }

    /** ***************************** Update user information ******************************** **/
    suspend fun updateUserInformation(user: User): Map<String, Any?> {
        return apiService.updateUserInformation(
            token = token,
            id = user.id,
            userData = user.toMapToUpdate()
        )
    }

}