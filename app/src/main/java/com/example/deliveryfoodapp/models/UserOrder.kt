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
            "created_at" to createdAt.format(formatter),
            "status" to status,
            "items_total_price" to itemsTotalPrice,
            "delivery_note" to deliveryNote,
            "delivery_person_id" to deliveryPersonID,
            "order_items" to orderItems.map { it.toMap() }
        )
    }

    companion object {
        @SuppressLint("NewApi")
        fun fromMap(map: Map<String, Any?>): UserOrder {
            val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
            return UserOrder(
                id = (map["id"] as Double).toInt(),
                restaurant = Restaurant.fromMap(map["restaurant"] as? Map<String, Any> ?: emptyMap()),
                createdAt = LocalDateTime.parse(map["created_at"] as String, formatter),
                status = (map["status"] as Double).toInt(),
                itemsTotalPrice = map["items_total_price"] as Double,
                deliveryNote = map["delivery_note"] as String?,
                deliveryPersonID = (map["delivery_person_id"] as Double).toInt(),
                orderItems = (map["order_items"] as? List<Map<String, Any>>)?.map {
                    OrderItem.fromMap(it)
                }?.toMutableList() ?: mutableListOf()
            )
        }
    }
}