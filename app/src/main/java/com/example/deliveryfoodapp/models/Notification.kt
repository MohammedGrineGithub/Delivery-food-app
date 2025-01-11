package com.example.deliveryfoodapp.models

import android.annotation.SuppressLint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Notification(
    var id : Int,
    var restaurantName: String,
    var message: String,
    var createdAt: LocalDateTime
) {
    @SuppressLint("NewApi")
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "restaurant_name" to restaurantName,
        "message" to message,
        "created_at" to createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    )

    companion object {
        @SuppressLint("NewApi")
        fun fromMap(map: Map<String, Any>) = Notification(
            id = (map["id"] as Double).toInt(),
            message = map["message"] as String,
            restaurantName = map["restaurant_name"] as String,
            createdAt = LocalDateTime.parse(map["createdAt"] as? String ?: "2000-01-01T00:00:00")
        )
        @SuppressLint("NewApi")
        fun emptyNotification() : Notification {
            return Notification(id = 0, restaurantName = "", message = "", createdAt = LocalDateTime.MIN)
        }
    }
}
