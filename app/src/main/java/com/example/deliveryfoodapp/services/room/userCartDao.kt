package com.example.deliveryfoodapp.services.room

import androidx.room.*

@Dao
interface UserCartDao {

    // Insert a new UserCart
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserCart(userCart: UserCart)

    // Update an existing UserCart
    @Update
    suspend fun updateUserCart(userCart: UserCart)

    // Delete a UserCart
    @Delete
    suspend fun deleteUserCart(userCart: UserCart)

    // Get a UserCart by ID
    @Query("SELECT * FROM userCarts WHERE id = :id")
    suspend fun getUserCartById(id: Int): UserCart?

    // Get all UserCarts
    @Query("SELECT * FROM userCarts")
    suspend fun getAllUserCarts(): List<UserCart>
}