package com.example.deliveryfoodapp.services.repositories

import com.example.deliveryfoodapp.services.room.AppDatabase
import com.example.deliveryfoodapp.services.room.OrderItem

object OrderItemRepository {

    // Add an OrderItem
    suspend fun addOrderItem(orderItem: OrderItem) {
        AppDatabase.buildDatabase()?.orderItemDao()?.insertOrderItem(orderItem)
    }

    // Get all OrderItems for a specific UserCart
    suspend fun getAllOrderItemsForUserCart(userCartId: Int) =
        AppDatabase.buildDatabase()?.orderItemDao()?.getOrderItemsByUserCartId(userCartId)

    // Remove an OrderItem by its ID
    suspend fun removeOrderItem(orderItem: OrderItem) {
        AppDatabase.buildDatabase()?.orderItemDao()?.deleteOrderItem(orderItem)
    }
}
