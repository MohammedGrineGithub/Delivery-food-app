package com.example.deliveryfoodapp.models

data class OrderItem(
    var id : Int,
    var orderId: Int,
    var itemId: Int,
    var note: String?,
    var itemQuantity: Int
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "orderId" to orderId,
        "itemId" to itemId,
        "note" to (note ?: ""),
        "itemQuantity" to itemQuantity
    )

    companion object {
        fun fromMap(map: Map<String, Any>) = OrderItem(
            id = map["id"] as? Int ?: -1,
            orderId = map["orderId"] as Int,
            itemId = map["itemId"] as Int,
            note = map["note"] as? String,
            itemQuantity = map["itemQuantity"] as Int
        )
    }
}
