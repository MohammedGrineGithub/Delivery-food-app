package com.example.deliveryfoodapp.backend_services.user_api

import Location
import com.example.deliveryfoodapp.backend_services.ApiClient
import com.example.deliveryfoodapp.local_storage_services.Pref
import com.example.deliveryfoodapp.models.Notification
import com.example.deliveryfoodapp.models.User
import com.example.deliveryfoodapp.models.UserOrder

object UserEndpoints {

    private val apiService = ApiClient.retrofit.create(UserApiService::class.java)
    private val token = Pref.getUserToken() ?: ""

    /** ***************************** User Sign Up ******************************** **/
    suspend fun userRegister(
        fullName: String,
        email: String,
        phone: String,
        location: Location,
        password: String
    ): Int {
        val userID = apiService.userRegister(
            body = mapOf(
                "full_name" to fullName,
                "email" to email,
                "phone" to phone,
                "location" to location.toMap(),
                "password" to password
            )
        )
        return (userID["user_id"] as Double).toInt()
    }

    /** ***************************** User Log In ******************************** **/
    suspend fun userLogin(email: String, password: String): Int {
        val userID = apiService.userLogin(
            body = mapOf(
                "email" to email,
                "password" to password
            )
        )
        return (userID["user_id"] as Double).toInt()
    }

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

    /** ***************************** Get all user orders ******************************** **/
    suspend fun fetchAllUserOrdersByUserID(id : Int) : MutableList<UserOrder> {
        val orders = apiService.getOrdersByUserID(
            id = id,
            token = token
        )
        return orders.map { UserOrder.fromMapToAll(it) }.toMutableList()
    }

    /** ***************************** Get order details ******************************** **/
    suspend fun fetchOrderDetailsByOrderID(id: Int): UserOrder {
        val orderDetails = apiService.getOrderDetailsByOrderID(
            id = id,
            token = token
        )
        return UserOrder.fromMapToDetails(orderDetails)
    }

    /** *****************************  Rate a restaurant ******************************** **/
    suspend fun rateRestaurant(restaurantID : Int, rating : Double, comment : String, orderID : Int): Map<String, Any?> {
        return apiService.rateRestaurant(
            token = token,
            body = mapOf(
                "id" to restaurantID,
                "rating" to rating,
                "comment" to comment,
                "order_id" to orderID
            )
        )
    }

    /** *****************************  create order ******************************** **/
    suspend fun createOrder(userID : Int, userOrder: UserOrder): Map<String, Any?> {
        return apiService.createOrder(
            token = token,
            body = mapOf(
                "user_id" to userID,
                "order" to mapOf(
                    "customer" to userID,
                    "restaurant" to userOrder.restaurant.id,
                    "total_price" to userOrder.itemsTotalPrice,
                    "status" to userOrder.status,
                    "delivery_note" to userOrder.deliveryNote
                ),
                "order_items" to userOrder.orderItems.map { mapOf(
                    "item" to it.item.id,
                    "note" to it.note,
                    "item_quantity" to it.itemQuantity
                ) }
            )
        )
    }

    /** ***************************** Update user information ******************************** **/
    suspend fun updateUserPhoneNumber(id: Int, phone: String): Map<String, Any?> {
        return apiService.updateUserPhoneNumber(
            token = token,
            id = id,
            phone = mapOf("phone" to phone)
        )
    }
    /** ***************************** Update user information ******************************** **/
    suspend fun changePassword(id: Int, currentPassword: String, newPassword: String): Map<String, Any?> {
        return apiService.changePassword(
            token = token,
            body = mapOf(
                "id" to id,
                "current_password" to currentPassword,
                "new_password" to newPassword
            )
        )
    }

}