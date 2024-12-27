package com.example.deliveryfoodapp.models

data class Order(
    val userId: Int,
    val restaurantId: Int,
    val totalPrice: Double,
    val createdAt: String,
    val status: Int,
    val deliveryNote: String?,
    val deliveryPersonId: Int?
) {
    fun toMap(): Map<String, Any> = mapOf(
        "userId" to userId,
        "restaurantId" to restaurantId,
        "totalPrice" to totalPrice,
        "createdAt" to createdAt,
        "status" to status,
        "deliveryNote" to (deliveryNote ?: ""),
        "deliveryPersonId" to (deliveryPersonId ?: -1)
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = Order(
            userId = map["userId"] as Int,
            restaurantId = map["restaurantId"] as Int,
            totalPrice = map["totalPrice"] as Double,
            createdAt = map["createdAt"] as String,
            status = map["status"] as Int,
            deliveryNote = map["deliveryNote"] as? String,
            deliveryPersonId = map["deliveryPersonId"] as? Int
        )
    }
}
