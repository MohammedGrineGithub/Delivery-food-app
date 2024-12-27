package com.example.deliveryfoodapp.models
data class Notification(
    val userId: Int,
    val orderId: Int,
    val message: String,
    val createdAt: String
) {
    fun toMap(): Map<String, Any> = mapOf(
        "userId" to userId,
        "orderId" to orderId,
        "message" to message,
        "createdAt" to createdAt
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = Notification(
            userId = map["userId"] as Int,
            orderId = map["orderId"] as Int,
            message = map["message"] as String,
            createdAt = map["createdAt"] as String
        )
    }
}
