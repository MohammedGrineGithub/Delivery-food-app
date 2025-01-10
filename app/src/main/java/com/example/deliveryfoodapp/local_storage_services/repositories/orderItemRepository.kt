package com.example.deliveryfoodapp.local_storage_services.repositories

import com.example.deliveryfoodapp.local_storage_services.room.AppDatabase
import com.example.deliveryfoodapp.local_storage_services.room.RoomOrderItem

object OrderItemRepository {

    // Add an OrderItem
    fun addOrderItem(roomOrderItem: RoomOrderItem):Long{
        val generatedId = AppDatabase.buildDatabase()?.orderItemDao()?.insertOrderItem(roomOrderItem)
        return generatedId ?: -1L
    }

    // Update OrderItem
    fun updateOrderItem(roomOrderItem: RoomOrderItem) {
        AppDatabase.buildDatabase()?.orderItemDao()?.updateOrderItem(roomOrderItem)
    }

    // Get an OrderItem by its ID
    fun getOrderItemById(orderItemId: Int): RoomOrderItem? {
        return AppDatabase.buildDatabase()?.orderItemDao()?.getOrderItemByID(orderItemId)
    }

    // Get an OrderItem by the item id
    fun getOrderItemByItemID(itemID: Int): RoomOrderItem? {
        return AppDatabase.buildDatabase()?.orderItemDao()?.getOrderItemByItemID(itemID)
    }

    // Get all OrderItems for a specific UserCart
    fun getAllOrderItemsForUserCart(userCartId: Int) =
        AppDatabase.buildDatabase()?.orderItemDao()?.getOrderItemsByUserCartId(userCartId)

    // Remove an OrderItem by its ID
    fun removeOrderItem(roomOrderItem: RoomOrderItem) {
        AppDatabase.buildDatabase()?.orderItemDao()?.deleteOrderItem(roomOrderItem)
    }

    fun clearOrderItems() {
        AppDatabase.buildDatabase()?.orderItemDao()?.clearOrderItems()
    }
}
