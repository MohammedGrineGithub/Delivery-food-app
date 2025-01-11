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
        "user_id" to userId,
        "restaurant_id" to restaurantId,
        "total_price" to totalPrice,
        "created_at" to createdAt,
        "status" to status,
        "delivery_note" to (deliveryNote ?: ""),
        "delivery_person_id" to (deliveryPersonId ?: -1)
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = Order(
            id = (map["id"] as Double).toInt(),
            userId = (map["user_id"] as Double).toInt(),
            restaurantId = (map["restaurant_id"] as Double).toInt(),
            totalPrice = map["total_price"] as Double,
            createdAt = map["created_at"] as String,
            status = (map["status"] as Double).toInt(),
            deliveryNote = map["delivery_note"] as? String,
            deliveryPersonId = (map["delivery_person_id"] as Double).toInt()
        )
    }
}
