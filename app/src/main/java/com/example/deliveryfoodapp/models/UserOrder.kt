package com.example.deliveryfoodapp.models

import android.annotation.SuppressLint

data class UserOrder(
    var id: Int,
    var restaurant: Restaurant,
    var createdAt: String,
    var status: Int,
    var itemsTotalPrice: Double,
    var deliveryNote: String?,
    var deliveryPerson: DeliveryPerson = DeliveryPerson.emptyDeliveryPerson(),
    var orderItems: MutableList<OrderItem> = mutableListOf()
) {

    @SuppressLint("NewApi")
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "restaurant" to restaurant.toMap(),
            "created_at" to createdAt,
            "status" to status,
            "total_price" to itemsTotalPrice,
            "delivery_note" to deliveryNote,
            "delivery_person" to deliveryPerson.toMap(),
            "order_items" to orderItems.map { it.toMap() }
        )
    }

    companion object {
        fun fromMapToDetails(map: Map<String, Any?>): UserOrder {
            return UserOrder(
                id = (map["id"] as Double).toInt(),
                restaurant = Restaurant.fromMapToOrder(map["restaurant"] as? Map<String, Any> ?: emptyMap()),
                createdAt = map["created_at"] as String,
                status = (map["status"] as Double).toInt(),
                itemsTotalPrice = map["total_price"] as Double,
                deliveryNote = map["delivery_note"] as String?,
                deliveryPerson = DeliveryPerson.fromMap(map["delivery_person"] as? Map<String, Any> ?: emptyMap()),
                orderItems = (map["order_items"] as? List<Map<String, Any>>)?.map {
                    OrderItem.fromMap(it)
                }?.toMutableList() ?: mutableListOf()
            )
        }
        fun fromMapToAll(map: Map<String, Any?>): UserOrder {
            return UserOrder(
                id = (map["id"] as Double).toInt(),
                restaurant = Restaurant.fromMapToOrder(map["restaurant"] as? Map<String, Any> ?: emptyMap()),
                createdAt = map["created_at"] as String,
                status = (map["status"] as Double).toInt(),
                itemsTotalPrice = map["total_price"] as Double,
                deliveryNote = map["delivery_note"] as String?
            )
        }

        fun emptyUserOrder(): UserOrder {
            return UserOrder(
                id = 0,
                restaurant = Restaurant.emptyRestaurant(),
                createdAt = "",
                status = -1,
                deliveryNote = "",
                itemsTotalPrice = 0.0,
                deliveryPerson = DeliveryPerson.emptyDeliveryPerson(),
                orderItems = mutableListOf()
            )
        }
    }
}