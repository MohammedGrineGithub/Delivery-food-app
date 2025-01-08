package com.example.deliveryfoodapp.services.room

import androidx.room.*

@Dao
interface OrderItemDao {

    // Insert a new OrderItem
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderItem(orderItem: OrderItem)

    // Insert multiple OrderItems
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderItems(orderItems: List<OrderItem>)

    // Update an existing OrderItem
    @Update
    suspend fun updateOrderItem(orderItem: OrderItem)

    // Delete an OrderItem
    @Delete
    suspend fun deleteOrderItem(orderItem: OrderItem)

    // Get all OrderItems linked to a specific UserCart
    @Query("SELECT * FROM orderItems WHERE userCartId = :userCartId")
    suspend fun getOrderItemsByUserCartId(userCartId: Int): List<OrderItem>
}