package com.example.deliveryfoodapp.models

import android.annotation.SuppressLint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Notification(
    var id : Int,
    var userId: Int,
    var orderId: Int,
    var message: String,
    var createdAt: LocalDateTime
) {
    @SuppressLint("NewApi")
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "userId" to userId,
        "orderId" to orderId,
        "message" to message,
        "createdAt" to createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    )

    companion object {
        @SuppressLint("NewApi")
        fun fromMap(map: Map<String, Any>) = Notification(
            id = map["id"] as? Int ?: -1,
            userId = map["userId"] as Int,
            orderId = map["orderId"] as Int,
            message = map["message"] as String,
            createdAt = LocalDateTime.parse(map["createdAt"] as? String ?: "2000-01-01T00:00:00")
        )
        @SuppressLint("NewApi")
        fun emptyNotification() : Notification {
            return Notification(id = 0, userId = 0, orderId = 0, message = "", createdAt = LocalDateTime.MIN)
        }
    }
}
