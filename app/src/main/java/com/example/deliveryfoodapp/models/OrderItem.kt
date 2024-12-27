package com.example.deliveryfoodapp.models

data class OrderItem(
    val orderId: Int,
    val itemId: Int,
    val note: String?,
    val itemQuantity: Int
) {
    fun toMap(): Map<String, Any> = mapOf(
        "orderId" to orderId,
        "itemId" to itemId,
        "note" to (note ?: ""),
        "itemQuantity" to itemQuantity
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = OrderItem(
            orderId = map["orderId"] as Int,
            itemId = map["itemId"] as Int,
            note = map["note"] as? String,
            itemQuantity = map["itemQuantity"] as Int
        )
    }
}
