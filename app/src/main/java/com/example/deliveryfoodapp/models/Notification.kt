package com.example.deliveryfoodapp.models

data class Notification(
    var id : Int,
    var restaurantName: String,
    var message: String,
    var createdAt: String
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "restaurant_name" to restaurantName,
        "message" to message,
        "created_at" to createdAt
    )

    companion object {
        fun fromMap(map: Map<String, Any?>) = Notification(
            id = (map["id"] as Double).toInt(),
            message = map["message"] as String,
            restaurantName = map["restaurant_name"] as String,
            createdAt = map["created_at"] as String
        )
        fun emptyNotification() : Notification {
            return Notification(id = 0, restaurantName = "", message = "", createdAt = "00:00:00")
        }
    }
}
