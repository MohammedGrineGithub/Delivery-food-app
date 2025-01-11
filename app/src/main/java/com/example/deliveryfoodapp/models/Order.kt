package com.example.deliveryfoodapp.models

data class Order(
    var id : Int,
    var userId: Int,
    var restaurantId: Int,
    var totalPrice: Double,
    var createdAt: String,
    var status: Int,
    var deliveryNote: String?,
    var deliveryPersonId: Int?
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
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
            id = (map["id"] as Double).toInt(),
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
