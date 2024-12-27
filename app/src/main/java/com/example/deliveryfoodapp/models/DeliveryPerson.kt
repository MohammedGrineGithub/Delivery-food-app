package com.example.deliveryfoodapp.models
data class DeliveryPerson(
    val fullName: String,
    val phone: String,
    val restaurantId: Int
) {
    fun toMap(): Map<String, Any> = mapOf(
        "fullName" to fullName,
        "phone" to phone,
        "restaurantId" to restaurantId
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = DeliveryPerson(
            fullName = map["fullName"] as String,
            phone = map["phone"] as String,
            restaurantId = map["restaurantId"] as Int
        )
    }
}
