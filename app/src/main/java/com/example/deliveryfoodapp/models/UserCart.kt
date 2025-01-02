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

    fun getOrderItemByItemID(item : Item) : OrderItem {
        var orderItem : OrderItem? = orderItems.find { it.item.id == item.id }

        if (orderItem == null) {
            orderItem = OrderItem.emptyOrderItem()

            /** orderItem.id = newOrderItemID  ## here i should create new order item id in sqlLite and a
            associate it here **/

            orderItem.item = item.copy()
            orderItem.itemQuantity = 1
            return  orderItem
        }
        return orderItem
    }

    private fun getOrderItemIndexByOrderItemID(orderItemID : Int) : Int {
        val index : Int? = orderItems.indexOfFirst { it.id == orderItemID }.takeIf { it != -1 }
        return index ?: -1
    }

    fun updateByOrderItem(orderItem: OrderItem) {
        val index = getOrderItemIndexByOrderItemID(orderItem.id)
        if (index == -1) {
            orderItems.add(orderItem)
        }else {
            orderItems[index] = orderItem.copy()
        }
    }

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