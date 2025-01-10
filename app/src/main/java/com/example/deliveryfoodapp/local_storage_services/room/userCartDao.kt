package com.example.deliveryfoodapp.local_storage_services.room

import androidx.room.*

@Dao
interface UserCartDao {

    // Insert a new UserCart
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserCart(roomUserCart: RoomUserCart): Long

    // Update an existing UserCart
    @Update
    fun updateUserCart(roomUserCart: RoomUserCart)

    // Delete a UserCart
    @Delete
    fun deleteUserCart(roomUserCart: RoomUserCart)

    // Get a UserCart by ID
    @Query("SELECT * FROM userCarts WHERE id = :id")
    fun getUserCartById(id: Int): RoomUserCart?

    // Get a UserCart by restaurantID
    @Query("SELECT * FROM userCarts WHERE restaurantID = :restaurantID")
    fun getUserCartByRestaurantID(restaurantID: Int): RoomUserCart?

    // Get all UserCarts
    @Query("SELECT * FROM userCarts")
    fun getAllUserCarts(): List<RoomUserCart>

    @Query("DELETE FROM userCarts")
    fun clearUserCarts()
}