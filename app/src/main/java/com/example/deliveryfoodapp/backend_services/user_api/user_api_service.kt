package com.example.deliveryfoodapp.backend_services.user_api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApiService {

    /** ***************************** Get user information ******************************** **/
    @GET("user/details/{id}/")
    suspend fun getUserById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Map<String, Any?>

    /** ***************************** Get user notifications ******************************** **/
    @GET("user/notifications/{id}/")
    suspend fun getNotificationsByUserID(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ) : MutableList<Map<String, Any?>>

    /** ***************************** Update user has notifications ******************************** **/
    @PUT("user/update_has_notifications/{id}/")
    suspend fun updateUserHasNotifications(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ) : Map<String, Any?>

    /** ***************************** Update user information ******************************** **/
    @PUT("user/updatev2/{id}/")
    suspend fun updateUserInformation(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body userData: Map<String, @JvmSuppressWildcards Any>
    ): Map<String, Any?>

    /** ***************************** Get all user orders ******************************** **/
    @GET("user/order_history/{id}/")
    suspend fun getOrdersByUserID(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ) : MutableList<Map<String, Any?>>

    /** ***************************** Update user order details ******************************** **/
    @GET("user/order_detail/{id}/")
    suspend fun getOrderDetailsByOrderID(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Map<String, Any?>

    /** ***************************** Rate a restaurant ******************************** **/
    @POST("user/rate_order/")
    suspend fun rateRestaurant(
        @Header("Authorization") token: String,
        @Body body: Map<String, @JvmSuppressWildcards Any>
    ): Map<String, Any?>

    /** ***************************** Create an order ******************************** **/
    @POST("user/order/")
    suspend fun createOrder(
        @Header("Authorization") token: String,
        @Body body: Map<String, @JvmSuppressWildcards Any>
    ): Map<String, Any?>

    /** ***************************** Update user phone number ******************************** **/
    @PUT("user/update/phone/{id}/")
    suspend fun updateUserPhoneNumber(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body phone: Map<String, @JvmSuppressWildcards String>
    ): Map<String, Any?>

    /** ***************************** change user password ******************************** **/
    @POST("user/change_password/")
    suspend fun changePassword(
        @Header("Authorization") token: String,
        @Body body: Map<String, @JvmSuppressWildcards Any>
    ): Map<String, Any?>
}