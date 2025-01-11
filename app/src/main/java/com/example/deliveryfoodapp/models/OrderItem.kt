package com.example.deliveryfoodapp.models

data class OrderItem(
    var id: Int,
    var item: Item,
    var note: String?,
    var itemQuantity: Int
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "item" to item.toMap(),
        "note" to (note ?: ""),
        "item_quantity" to itemQuantity
    )

    fun totalPrice() : Double {
        return item.price * itemQuantity
    }

    companion object {
        fun fromMap(map: Map<String, Any>) = OrderItem(
            id = (map["id"] as Double).toInt(),
            item = Item.fromMap(map["item"] as? Map<String, Any> ?: emptyMap()),
            note = map["note"] as? String,
            itemQuantity = (map["item_quantity"] as Double).toInt()
        )

        fun emptyOrderItem() : OrderItem{
            return OrderItem(
                id = 0,
                item = Item.emptyItem(),
                note = "",
                itemQuantity = 0
            )
        }
    }
}