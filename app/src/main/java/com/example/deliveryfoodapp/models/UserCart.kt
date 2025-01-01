package com.example.deliveryfoodapp.models

data class UserCart(
    var id: Int,
    var orderItems: MutableList<OrderItem>,
    var restaurantID: Int
) {
    fun toMap(): Map<String, Any> = mapOf(
        "id" to id,
        "orderItems" to orderItems.map { it.toMap() },
        "restaurantID" to restaurantID
    )

    fun totalItems() : Int {
        return orderItems.sumOf { it.itemQuantity }
    }

    fun totalPrice(): Double {
        return orderItems.sumOf { it.totalPrice() }
    }

    companion object {
        fun fromMap(map: Map<String, Any>) = UserCart(
            id = map["id"] as? Int ?: -1,
            orderItems = (map["orderItems"] as? List<Map<String, Any>>)?.map {
                OrderItem.fromMap(it)
            }?.toMutableList() ?: mutableListOf(),
            restaurantID = map["restaurantID"] as? Int ?: -1
        )

        fun emptyUserCart() : UserCart{
            return UserCart(
                id = 0,
                orderItems = mutableListOf(),
                restaurantID = 0
            )
        }
    }
}