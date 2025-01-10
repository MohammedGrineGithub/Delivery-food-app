package com.example.deliveryfoodapp.models

import android.annotation.SuppressLint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class UserOrder(
    var id: Int,
    var restaurant: Restaurant,
    var createdAt: LocalDateTime,
    var status: Int,
    var itemsTotalPrice: Double,
    var deliveryNote: String?,
    var deliveryPersonID: Int,
    var orderItems: MutableList<OrderItem>
) {
    fun getDeliveryPerson(): DeliveryPerson {
        return restaurant.getDeliveryPersonByItID(deliveryPersonID)
    }

    @SuppressLint("NewApi")
    fun toMap(): Map<String, Any?> {
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        return mapOf(
            "id" to id,
            "restaurant" to restaurant.toMap(),
            "createdAt" to createdAt.format(formatter),
            "status" to status,
            "itemsTotalPrice" to itemsTotalPrice,
            "deliveryNote" to deliveryNote,
            "deliveryPersonID" to deliveryPersonID,
            "orderItems" to orderItems.map { it.toMap() }
        )
    }

    companion object {
        @SuppressLint("NewApi")
        fun fromMap(map: Map<String, Any?>): UserOrder {
            val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
            return UserOrder(
                id = map["id"] as Int,
                restaurant = Restaurant.fromMap(map["restaurant"] as? Map<String, Any> ?: emptyMap()),
                createdAt = LocalDateTime.parse(map["createdAt"] as String, formatter),
                status = map["status"] as Int,
                itemsTotalPrice = map["itemsTotalPrice"] as Double,
                deliveryNote = map["deliveryNote"] as String?,
                deliveryPersonID = map["deliveryPersonID"] as Int,
                orderItems = (map["orderItems"] as? List<Map<String, Any>>)?.map {
                    OrderItem.fromMap(it)
                }?.toMutableList() ?: mutableListOf()
            )
        }
    }
}