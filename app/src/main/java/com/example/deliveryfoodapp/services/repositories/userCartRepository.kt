package com.example.deliveryfoodapp.services.repositories

import com.example.deliveryfoodapp.services.room.AppDatabase
import com.example.deliveryfoodapp.services.room.RoomUserCart

object UserCartRepository {

    // Add a UserCart
    fun addUserCart(roomUserCart: RoomUserCart): Long {
        val generatedId = AppDatabase.buildDatabase()?.userCartDao()?.insertUserCart(roomUserCart)
        return generatedId ?: -1L  // Return -1 if the insertion fails
    }

    // Get all UserCarts
    fun getAllUserCarts() =
        AppDatabase.buildDatabase()?.userCartDao()?.getAllUserCarts()

    // Get a UserCart by its ID
    fun getUserCartById(userCartId: Int) =
        AppDatabase.buildDatabase()?.userCartDao()?.getUserCartById(userCartId)

    // Get a UserCart by restaurantID
    fun getUserCartByRestaurantID(restaurantID: Int) =
        AppDatabase.buildDatabase()?.userCartDao()?.getUserCartByRestaurantID(restaurantID)

    // Remove a UserCart by its ID
    fun removeUserCart(roomUserCart: RoomUserCart) {
        AppDatabase.buildDatabase()?.userCartDao()?.deleteUserCart(roomUserCart)
    }
    fun clearUserCarts() {
        AppDatabase.buildDatabase()?.userCartDao()?.clearUserCarts()
    }
}