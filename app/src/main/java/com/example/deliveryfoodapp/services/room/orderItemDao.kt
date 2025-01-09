package com.example.deliveryfoodapp.services.room

import androidx.room.*

@Dao
interface OrderItemDao {

    // Insert a new OrderItem
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrderItem(roomOrderItem: RoomOrderItem): Long

    // Update an existing OrderItem
    @Update
    fun updateOrderItem(roomOrderItem: RoomOrderItem)

    // Delete an OrderItem
    @Delete
    fun deleteOrderItem(roomOrderItem: RoomOrderItem)

    // Get all OrderItems linked to a specific UserCart
    @Query("SELECT * FROM orderItems WHERE userCartId = :userCartId")
    fun getOrderItemsByUserCartId(userCartId: Int): List<RoomOrderItem>

    // Get OrderItem with it ID
    @Query("SELECT * FROM orderItems WHERE id = :id")
    fun getOrderItemByID(id : Int) : RoomOrderItem?

    // Get OrderItem with Item ID
    @Query("SELECT * FROM orderItems WHERE itemID = :itemID")
    fun getOrderItemByItemID(itemID: Int): RoomOrderItem?

    @Query("DELETE FROM orderItems")
    fun clearOrderItems()
}